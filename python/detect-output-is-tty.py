# Detect if stdout is a terminal
if sys.stdout.isatty():
    # output to a real terminal
    ...
else:
    # output being piped or redirected
    ...
