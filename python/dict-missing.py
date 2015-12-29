>>> class MyDict(dict):
...     def __init__(self, factory):
...         self.factory = factory
...     def __missing__(self, key):
...         self[key] = self.factory(key)
...         return self[key]
...
>>> d = MyDict(lambda x: -x)
>>> d[1]
-1
>>> d
{1: -1}
