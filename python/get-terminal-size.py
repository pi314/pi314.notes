def get_terminal_size ():
    import fcntl, struct, termios
    default_size = (25, 80)
    try:
        for fd in (0, 1, 2):
            v = struct.unpack('hh', fcntl.ioctl(fd, termios.TIOCGWINSZ, '*' * 4) )
            if v:
                return v
    except OSError:
        return default_size

print(get_terminal_size())
