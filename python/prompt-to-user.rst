.. code:: python

    def ask(prompt, given_options=''):
        '''
        ask(prompt)
        ask(prompt, ['y', 'n'])
        ask(prompt, 'yn')
        ask(prompt, 'yes no')
        
        Returns None on EOFError and KeyboardInterrupt.
        '''

        if isinstance(given_options, str):
            options = given_options.split()
            if len(options) == 1:
                options = [c for c in given_options]

        elif isinstance(given_options, list):
            options = given_options

        options = [o.lower() for o in options]
        if options:
            options[0] = options[0][0].upper() + options[0][1:]

        if options == []:   # str input
            try:
                return input(prompt + '> ')
            except (EOFError, KeyboardInterrupt):
                return None

        try:
            ret = input('{prompt} [{opts}] '.format(
                prompt=prompt,
                opts='/'.join(options)
            )).strip().lower()

            if ret == '':
                ret = options[0]

            else:
                matches = list(filter(lambda x: x.startswith(ret), options))
                if len(matches) == 0:
                    ret = None
                else:
                    ret = matches[0]

        except (EOFError, KeyboardInterrupt):
            return None

        return ret.lower()
