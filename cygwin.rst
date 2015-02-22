===========
Cygwin 筆記
===========

* ``/etc/password`` 可以更改預設的 shell

Cygwin 特有指令
---------------

* ``cygstart``

  - 用預設的程式開啟檔案

* ``cygcheck``

  - ``cygcheck -p``

    + 用 regex 搜尋 package

好用 Package
------------

* ``inetutils``

  - ``telnet``

* ``nc``
* ``pv``
* ``wget``
* ``bind-utils``

  - ``host``
  - ``dig``
  - ``nslookup``

* ``vim``
* ``zsh``
* ``util-linux``

  - ``whereis``
  - ``cal``

* ``gcc-core``
* ``gcc-g++``
* ``libgcc1``

* ``make``
* ``python``
* ``git``

* ``libiconv``

  - ``iconv``

* ``mosh``

  - https://gist.github.com/eerohele/2349067

X11
----

需安裝 Package
""""""""""""""

* ``xorg-server``
* ``xinit``

啟動
""""

1.  設定 DISPLAY 環境變數為 ``localhost:0``
2.  ``startwin``
3.  ``ssh -Y username@host``
4.  ``xterm &``

關閉 X Server
"""""""""""""

把 ``/usr/bin/XWin`` ``kill`` 掉
