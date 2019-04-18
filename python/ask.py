def ask(prompt, user_options):
    if isinstance(user_options, str):
        if len(user_options.split()) == 1:
            options = [c for c in user_options]
        else:
            options = user_options.split()

    elif isinstance(user_options, list):
        options = [o for o in user_options]

    else:
        raise ValueError

    def prettify(idx, opt):
        return (opt[0].upper() if idx == 0 else opt[0]) + opt[1:]

    opts = [o[0] for o in options]
    options = list(map(lambda x: prettify(*x), enumerate(options)))

    with open('/dev/tty') as tty:
        stdin_backup, sys.stdin = sys.stdin, tty
        try:
            ret = input('{prompt} [{options}] '.format(
                prompt=prompt.strip(),
                options='/'.join(options),
            )).strip().lower()
            if not ret:
                ret = opts[0]
        except (EOFError, KeyboardInterrupt):
            ret = 'n'

        sys.stdin = stdin_backup

    return ret.lower()
