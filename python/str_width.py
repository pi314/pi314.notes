from unicodedata import east_asian_width

def str_width(s):
    return sum(1 + (east_asian_width(c) in 'WF') for c in s)


assert str_width('測試') == 4
