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

def run(cmd, stdin=None, stdout=None, stderr=None, env=None):
    '''
    A line-oriented wrapper for running external commands

    :param iterable[str] cmd: The command to run.

    :param iterable[str] stdin:
        The input data, one item for each line without trailing newline.
        ``None`` means no data to input

    :param callable[str] stdout:
        The callback function that handles a stdout line.
        ``False`` means not to capture stdout.

    :param callable[str] stderr:
        The callback function that handles a stderr line.
        ``False`` means not to capture stderr.

    :param dict[str, str] env: The environment variables.
    :return: A CommandResult object.
    '''

    if stdin:
        stdin = (stdin,) if isinstance(stdin, str) else tuple(stdin)
        if not all(isinstance(line, str) for line in stdin):
            raise TypeError('stdin should be a tuple[str]')

    if stdout is None:
        stdout = lambda x: None
    elif stdout and not callable(stdout):
        raise TypeError('stdout should be a callable')

    if stderr is None:
        stderr = lambda x: None
    elif stderr and not callable(stderr):
        raise TypeError('stderr should be a callable')

    cmd = [str(token) for token in cmd]

    ret = CommandResult(cmd)
    ret.stdout = []
    ret.stderr = []

    output_queue = queue.Queue()

    p = sub.Popen(cmd,
            stdin=None if not stdin else sub.PIPE,
            stdout=None if stdout is False else sub.PIPE,
            stderr=None if stderr is False else sub.PIPE,
            encoding='utf-8', bufsize=1, universal_newlines=True,
            env=env)

    ostreams = [p.stdout, p.stderr]
    othreads = [None, None]

    def reader(idx, stream):
        for line in stream:
            line = line.rstrip('\n')
            output_queue.put((idx, line))
        output_queue.put((idx, None))

    for idx, _ in enumerate(othreads):
        if ostreams[idx]:
            othreads[idx] = threading.Thread(target=reader, args=(idx, ostreams[idx]))
            othreads[idx].daemon = True
            othreads[idx].start()

    # Feed stdin
    if p.stdin:
        ret.stdin = stdin
        p.stdin.write('\n'.join(stdin))
        p.stdin.flush()
        p.stdin.close()

    callbacks = [stdout, stderr]
    outputs = [ret.stdout, ret.stderr]
    eof_flags = [t is None for t in othreads]

    # Collect all outputs
    while not (eof_flags[0] and eof_flags[1]):
        idx, line = output_queue.get()

        callbacks[idx](line)

        if line is None:
            eof_flags[idx] = True
        else:
            outputs[idx].append(line)

        if eof_flags[0] and eof_flags[1]:
            break

    # Gracefully wait for threads and child process to finish
    for t in othreads:
        t.join()

    p.wait()

    ret.returncode = p.returncode

    return ret
