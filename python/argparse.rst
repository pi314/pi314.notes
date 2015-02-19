===============
Python argparse
===============

Python ``argparse`` module 是個 framework，方便的處理參數的邏輯

* 初始化 ::

    import argparse
    parser = argparse.ArgumentParser(description='說明文字')

* ``-a`` 格式的參數 ::

    parser.add_argument('-a', action='store_true', help='說明文字')

* ``-f file ...`` 格式的參數 ::

    parser.add_argument('-f', nargs=1)

  - ``nargs`` 指定 ``-f`` 後面可接的參數數量，可用的選項有

    + 整數
    + ``'?'`` 表示可接 1 個或 0 個參數，需搭配 ``default=預設值`` 使用
    + ``'*'`` 表示任意數量的參數，並輸出成一個 ``list``
    + ``'+'`` 表示 1 個以上的參數，並輸出成一個 ``list``
    + ``argparse.REMAINDER`` 表示吃掉剩下的所有參數，並輸出成一個 ``list``

