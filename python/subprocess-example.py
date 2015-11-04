import subprocess as sub

try:
    out = sub.check_output(['ls', '-a', '-l'])

except sub.CalledProcessError:
    print('Command return code is not 0')
