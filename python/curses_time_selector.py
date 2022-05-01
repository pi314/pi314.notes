import curses
import datetime

from curses import wrapper


def prev_value(x, field):
    limit = 24 if field == 'hh' else 60
    return (x + limit - 1) % limit


def next_value(x, field):
    limit = 24 if field == 'hh' else 60
    return (x + 1) % limit


def render(last_key, stdscr, offsets, now, focus):
    offsety = offsets[0]
    offsetx = offsets[0]

    fmt = '{hh:0>2}   {mm:0>2}   {ss:0>2}'

    lines = [
            fmt.format(
                hh=prev_value(now['hh'], 'hh') if focus == 'hh' else '  ',
                mm=prev_value(now['mm'], 'mm') if focus == 'mm' else '  ',
                ss=prev_value(now['ss'], 'ss') if focus == 'ss' else '  ',
                ),
            fmt.format(
                hh=now['hh'],
                mm=now['mm'],
                ss=now['ss'],
                ),
            fmt.format(
                hh=next_value(now['hh'], 'hh') if focus == 'hh' else '  ',
                mm=next_value(now['mm'], 'mm') if focus == 'mm' else '  ',
                ss=next_value(now['ss'], 'ss') if focus == 'ss' else '  ',
                ),
            ]

    for idx, line in enumerate(lines):
        if idx == 1:
            attr = curses.color_pair(0)
        else:
            attr = curses.color_pair(1) | curses.A_BOLD

        stdscr.addstr(idx + offsety, offsetx + 1, line, attr)

    focus_l, focus_r = None, None
    if focus == 'hh':
        focus_l, focus_r = 0, 3
    elif focus == 'mm':
        focus_l, focus_r = 5, 8
    elif focus == 'ss':
        focus_l, focus_r = 10, 13

    if focus_l is not None and focus_r is not None:
        stdscr.addstr(offsety + 1, offsetx + focus_l, '[')
        stdscr.addstr(offsety + 1, offsetx + focus_r, ']')

    stdscr.addstr(offsety + 1, offsetx + 4, ':')
    stdscr.addstr(offsety + 1, offsetx + 9, ':')


def main(stdscr):
    stdscr.clear()

    curses.curs_set(False)

    curses.init_pair(1, curses.COLOR_BLACK, curses.COLOR_BLACK)

    lines = [
            '┌' + '─' * 14 + '┐',
            '│' + ' ' * 14 + '│',
            '│' + ' ' * 14 + '│',
            '│' + ' ' * 14 + '│',
            '└' + '─' * 14 + '┘',
            ]
    for idx, line in enumerate(lines):
        attr = curses.color_pair(1) | curses.A_BOLD
        stdscr.addstr(idx, 0, line, attr)

    fields = ['hh', 'mm', 'ss']
    now = datetime.datetime.now()
    now = {'hh': now.hour, 'mm': now.minute, 'ss': now.second}

    focus = 0
    key = 'none'
    while True:
        render(key, stdscr, (1, 1), now, fields[focus])
        key = stdscr.getkey()

        if key == 'q':
            break

        elif key in ('h', 'KEY_LEFT'):
            focus = max(0, focus - 1)

        elif key in ('l', 'KEY_RIGHT'):
            focus = min(len(fields) - 1, focus + 1)

        elif key in ('k', 'KEY_UP'):
            now[fields[focus]] = prev_value(now[fields[focus]], fields[focus])

        elif key in ('j', 'KEY_DOWN'):
            now[fields[focus]] = next_value(now[fields[focus]], fields[focus])

        elif key == '0':
            now[fields[focus]] = 0

        elif key == '$':
            now[fields[focus]] = prev_value(0, fields[focus])

    return now['hh'], now['mm'], now['ss']


if __name__ == '__main__':
    print('Selected time: {:0>2}:{:0>2}:{:0>2}'.format(*wrapper(main)))
