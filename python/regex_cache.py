import re


class RegexCache:
    def __init__(self, text):
        self.text = text
        self.m = None

    def match(self, regex):
        self.m = re.match(regex, self.text)
        return self.m

    def group(self, *group_id):
        return self.m.group(*group_id)

    def groups(self):
        return self.m.groups()
