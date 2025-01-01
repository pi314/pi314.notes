===============================================================================
Python argparse
===============================================================================
Python ``argparse`` module 是個用來處理參數邏輯的 framework


速查
-------------------------------------------------------------------------------
.. code:: python

  import argparse
  parser = argparse.ArgumentParser(description='說明文字', prog='PROG')

  subparsers = parser.add_subparsers(title='subcommands')
  parser_banana = subparsers.add_parser('banana', help='Banana help')
  parser_banana.set_defaults(subcmd='banana')

  # -a
  parser_banana.add_argument('-a', action='store_true', help='說明文字')

  # -f file
  parser_banana.add_argument('-f', dest='files')

  # -f file ...
  parser_banana.add_argument('-f', nargs='*', dest='files')

  # a b c ...
  parser_banana.add_argument('val')

  # parse it
  args = parser.parse_args()

  # parse "intermixed" positional and optional arguments
  args = parser.parse_intermixed_args()


``add_argument`` 筆記
-------------------------------------------------------------------------------

::

  import argparse
  parser = argparse.ArgumentParser(description='說明文字')
  parser.add_argument(...)

``add_argument`` 可以傳入很多參數，調整參數的行為

* ``dest``

  - 指定負責儲存值的變數名稱

* ``action``

  - ``action="store_true"`` ``action="store_false"``

    + ``-a`` 讓格式的參數儲存 ``True``/``False`` 的值

  - ``action="store_const", const=<value>``

    + ``-a`` 讓格式的參數儲存 ``<value>`` 的值， ``store_true`` 和 ``store_false`` 算是特殊狀況

  - ``action="append"``

    + 讓 ``-a 1 -a 2`` 存入 ``[1, 2]``

  - ``action="append_const"``

    + 等於 ``append`` 加上 ``store_const``

* ``choices=[1, 2, 3]``

  - 限制參數的值域

* ``default="<value>"``

  - 若 ``-a`` 參數完全沒有出現，則存入 ``<value>``

* ``const="<value>"``

  - 若 ``-a`` 參數有被給予，則存入 ``<value>``

* ``nargs``

  - 可用的選項有

    + ``nargs=整數``
    + ``nargs='?'`` 表示可接 1 個或 0 個參數，需搭配 ``default=預設值`` 使用
    + ``nargs='*'`` 表示任意數量的參數，並輸出成一個 ``list``
    + ``nargs='+'`` 表示 1 個以上的參數，並輸出成一個 ``list``
    + ``nargs=argparse.REMAINDER`` 表示吃掉剩下的所有參數，並輸出成一個 ``list``

  - 可以和 ``default`` 以及 ``const`` 搭配，e.g. ``nargs="?", default="d", const="c"``

    + 若 ``-a`` 參數沒有被給予，存入 ``"d"``
    + 若給予 ``-a`` ，存入 ``"c"``
    + 若給予 ``-a v`` ，存入 ``"v"``
