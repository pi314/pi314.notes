import difflib


def color_str(code):
    return lambda x: '\033[3{}m{}\033[m'.format(code, x)


red = color_str(1)
green = color_str(2)
yellow = color_str(3)


a = 'qabxcd'
b = 'abycdf'

s = difflib.SequenceMatcher(None, a, b)

A = ''
B = ''
for tag, i1, i2, j1, j2 in s.get_opcodes():
    if tag == 'equal':
        A += a[i1:i2]
        B += b[j1:j2]

    elif tag == 'delete':
        A += red(a[i1:i2])
        B += ' ' * (i2 - i1)

    elif tag == 'insert':
        A += ' ' * (j2 - j1)
        B += green(b[j1:j2])

    elif tag == 'replace':
        A += yellow(a[i1:i2])
        B += yellow(b[i1:i2])

print('a', '=', a)
print('b', '=', b)

print()
print('A', '=', A)
print('B', '=', B)
