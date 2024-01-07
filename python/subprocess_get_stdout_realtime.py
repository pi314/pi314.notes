### Some code snippet that may be handy to fork a child process and capture its
### stdout in semi-realtime.

### Note that if the child process buffers stdout,
### you can't actually do anything on it. Game over. Go to sleep. Bye.

# subprocess.Popen(text=True) needs Python3.7 and above (use universal_newlines for older versions)


# =============================================================================
# Script: sample stdout producer
# -----------------------------------------------------------------------------

import sys
import time

for i in range(6):
    print('stdout line', i)
    print('stderr line', i, file=sys.stderr)
    sys.stdout.flush()
    sys.stderr.flush()
    time.sleep(0.5)


# =============================================================================
# Script: progress bar
# -----------------------------------------------------------------------------

import sys
import time

width = 20
for i in range(width):
    print('\r[' + ('=' * int(width * (i / width))).ljust(width) + ']', end='')
    sys.stdout.flush()
    time.sleep(0.2)


# =============================================================================
# Method 1: using subprocess and poll()
# -----------------------------------------------------------------------------

import subprocess as sub

p = sub.Popen(['command', 'arg1', 'arg2'], stdout=sub.PIPE, bufsize=1, text=True)
while p.poll() == None:
    data = p.stdout.readline()
    print(data)

return_code = p.poll()


# You may need to remove text=True for preventing Python translate '\r' to '\n'
# in order to display a real-time progress bar.

# By removing text=True, bufsize=1 also needs to be removed because buffering
# is not supported in binary mode.

# So ``data`` is now in ``bytes`` type, and you have to .decode() it.

# Make sure decode errors are handled as your program may pull stdout faster
# than the source.


# =============================================================================
# Method 2: just using for line in stdout
# -----------------------------------------------------------------------------

import subprocess as sub

p = sub.Popen(['command', 'arg1', 'arg2'], stdout=sub.PIPE, bufsize=1, text=True)

for line in p.stdout:
    line = line.rstrip('\n')
    print(line)


# =============================================================================
# Utility: A threaded line-oriented run() utility
# -----------------------------------------------------------------------------

import queue
import subprocess as sub
import threading


class CommandResult:
    def __init__(self, cmd):
        self.cmd = list(cmd)
        self.stdin = None
        self.stdout = None
        self.stderr = None
        self.returncode = None

def run(cmd, stdin=None, stdout=True, stderr=True, newline='\n', env=None):
    '''
    A line-oriented wrapper for running external commands.

    :param iterable[str] cmd: The command to run.

    :param iterable[str] stdin:
        The input text, one item for each line, without trailing newline.
        It could be left ``None`` if there is nothing to input.

    :param None|bool|callable[str] stdout:
        If set to ``None``, stdout will be left as-is (mostly like to the tty).
        If set to ``True``, stdout will be accumulated into ret.stdout.
        If set to ``False``, stdout will be silently dropped.
        If set to a callable, it will be called with each line as the argument.

    :param None|bool|callable[str] stderr:
        If set to ``None``, stderr will be left as-is (mostly like to the tty).
        If set to ``True``, stderr will be accumulated into ret.stderr.
        If set to ``False``, stderr will be silently dropped.
        If set to a callable, it will be called with each line as the argument.

    :param dict[str, str] env: The environment variables.
    :return: A CommandResult object.
    '''

    if stdin:
        stdin = [stdin] if isinstance(stdin, str) else stdin

    if stdout is None or isinstance(stdout, bool):
        pass
    elif stdout and not callable(stdout):
        raise TypeError('stdout should be a callable')

    if stderr is None or isinstance(stderr, bool):
        pass
    elif stderr and not callable(stderr):
        raise TypeError('stderr should be a callable')

    cmd = [str(token) for token in cmd]

    ret = CommandResult(cmd)
    ret.stdin = []
    ret.stdout = None if stdout is False else []
    ret.stderr = None if stderr is False else []

    output_queue = queue.Queue()

    p = sub.Popen(cmd,
            stdin=None if not stdin else sub.PIPE,
            stdout=None if stdout is None else sub.PIPE,
            stderr=None if stderr is None else sub.PIPE,
            encoding='utf-8', bufsize=1, universal_newlines=True,
            env=env)

    def stdin_writer(idx, stream):
        for line in stdin:
            ret.stdin.append(line)
            stream.write(line + '\n')
            stream.flush()
        stream.close()
        output_queue.put((idx, None))

    def reader(idx, stream):
        for line in stream:
            line = line.rstrip('\n')
            output_queue.put((idx, line))
        output_queue.put((idx, None))

    io_worker = [stdin_writer, reader, reader]
    iostreams = [p.stdin, p.stdout, p.stderr]
    iothreads = [None, None, None]

    for idx, _ in enumerate(iothreads):
        if iostreams[idx]:
            iothreads[idx] = threading.Thread(target=io_worker[idx], args=(idx, iostreams[idx]))
            iothreads[idx].daemon = True
            iothreads[idx].start()

    output_dest = [
            None,
            stdout if callable(stdout) else ret.stdout,
            stderr if callable(stderr) else ret.stderr,
            ]
    eof_flags = [
            iothreads[0] is None,
            iothreads[1] is None,
            iothreads[2] is None,
            ]

    # Collect all outputs
    while not (eof_flags[0] and eof_flags[1] and eof_flags[2]):
        idx, line = output_queue.get()

        if callable(output_dest[idx]):
            output_dest[idx](line)
        elif isinstance(output_dest[idx], list) and line is not None:
            output_dest[idx].append(line)

        if line is None:
            eof_flags[idx] = True

        if eof_flags[0] and eof_flags[1] and eof_flags[2]:
            break

    # Gracefully wait for threads and child process to finish
    for t in iothreads:
        if t:
            t.join()

    p.wait()

    ret.returncode = p.returncode

    return ret
