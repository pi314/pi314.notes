from datetime import time


def my_timedelta(a_str, b_str):
    '''
    Accepts 'hh:mm:ss' formated time and return timedelta in same format
    TODO: support 'yyyy/mm/dd hh:mm:ss' format
    TODO: support customized format as optional argument
    '''
    def to_sec(t):
        return t[0] * 60 * 60 + t[1] * 60 + t[2]

    a_time = to_sec(tuple(map(int, a_str.split(':'))))
    b_time = to_sec(tuple(map(int, b_str.split(':'))))
    delta = b_time - a_time
    (hh, mm, ss) = (int(delta / 3600), int((delta % 3600) / 60), delta % 60)
    return '{}:{:>02}:{:>02}'.format(hh, mm, ss)


if __name__ == '__main__':
    print(my_timedelta('188:33:58', '199:36:13'))
