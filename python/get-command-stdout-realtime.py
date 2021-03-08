### Some code snippet that may be handy to fork a child process and capture its
### stdout in realtime (sort of; compared to process it after child process finishes)

### Note that if the child process buffers stdout,
### you can't actually do anything on it. Game over. Go to sleep. Bye.

# subprocess.Popen(text=True) needs Python3.7 and above (use universal_newlines for older versions)


# =============================================================================
# Script: sample stdout producer

import sys
import time

for i in range(6):
    print('stdout line', i)
    print('stderr line', i, file=sys.stderr)
    sys.stdout.flush()
    sys.stderr.flush()
    time.sleep(0.5)


# =============================================================================
# Method 1: using subprocess and poll()

import subprocess as sub

p = sub.Popen(['command', 'arg1', 'arg2'], stdout=sub.PIPE, bufsize=1, text=True)
while p.poll() == None:
    data = p.stdout.readline()
    print(data)

return_code = p.poll()


# =============================================================================
# Method 2: just using for line in stdout

import subprocess as sub

p = sub.Popen(['command', 'arg1', 'arg2'], stdout=sub.PIPE, bufsize=1, text=True)

for line in p.stdout:
    line = line.rstrip('\n')
    print(line)
