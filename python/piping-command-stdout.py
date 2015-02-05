# 即時取得指令的 stdout
p = sub.Popen(['command', 'arg1', 'arg2'], stdout=sub.PIPE, bufsize=1)
while p.poll() == None:
    data = p.stdout.readline()
    print( str(data, 'utf-8'))

return_code = p.poll()
