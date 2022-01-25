import getpass
import sys


def ask_user(user_prompt, user_options=None, password=False, catch=(KeyboardInterrupt, EOFError)):
    '''
    ask_user('prompt')
    ask_user('prompt', ('y', 'n'))
    ask_user('prompt', 'yn')

    Returns:
        user input
        None, when stdin is not a tty (e.g. a pipe)
        EOFError
        KeyboardInterrupt
    '''

    if not sys.stdin.isatty():
        return None

    elif user_options is None:
        options = []
        prompt = user_prompt + ('' if user_prompt.endswith(' ') else '> ')

    elif isinstance(user_options, (list, tuple, str)):
        if ' ' in user_options:
            raise ValueError('Cannot have space in user_options')

        options = (user_options[0].capitalize(),) + tuple(user_options[1:])
        prompt = '{prompt} [{options}] '.format(
            prompt=user_prompt.rstrip(),
            options='/'.join(options),
        )

    else:
        raise ValueError('Unrecognized user_options: {}'.format(user_options))

    try:
        if password:
            ret = getpass.getpass(prompt)
        else:
            ret = input(prompt).strip().lower()
            if options and not ret:
                ret = user_options[0]

    except catch as e:
        ret = type(e)

    return ret

if __name__ == '__main__':
    print(ask_user('Password> ', password=True))
    print(ask_user('prompt', catch=(KeyboardInterrupt,)))
    print(ask_user('prompt', 'yn', catch=(EOFError,)))
    print(ask_user('prompt', ('y', 'n')))
    print(ask_user('prompt', ('yes', 'no')))
    print(ask_user('prompt', ('apple', 'banana')))
    print(ask_user('prompt', 5))
    print(ask_user('prompt', 'yes no'))
