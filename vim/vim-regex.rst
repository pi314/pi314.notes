=============
= Vim Regex =
=============

* Regex lookahead/behind

  - 一樣會 match，但不算在最後 match 的字串中

  - ``\@<=`` positive lookbehind
  - ``\@<!`` negative lookbehind
  - ``\@=`` positive lookahead
  - ``\@!`` negative lookahead

  - 範例

    + 比對所有的 ``foo`` ，但後面不能接 ``bar`` ::
      
        foo\(bar\)\@!

    + 比對所有的 ``bar`` ，但前面不能接 ``foo`` ::

        \(foo\)\@<!bar

* ``\%(\)`` 可以把 pattern 包成 atom 但不算成 group
