=====================
= screen 用法及設定 =
=====================

用詞
====

本文件統一稱呼（可能非正式）

* 每個 ``screen`` process 為一個 ``session``
* ``screen`` 中的每一個頁面為一個 ``window``

command line arguments
======================

* 開啟一個新的 ``screen session`` ::

    $ screen

* 開啟一個新的 ``screen session`` 並取名為 ``<session_name>`` ::

    $ screen -S <session_name>

* 查看目前的 ``screen session`` 列表 ::

    $ screen -ls

  - 會列出 ``id`` ，如果有 ``session`` 名稱的話，也會列出

* attach 一個被 detach 的 ``session`` ::

    $ screen -r [id | session_name]

  - ``id``/``session_name`` 不需要完整提供，只要提供的長度足夠辨別即可

* 把當前的 ``screen session`` detach ::

    $ screen -d

* attach 一個 ``session`` ，若有必要的話，先 detach ::

    $ screen -rd

  - 突然斷網時， ``screen`` 會停留在 attach 狀態，此時無法 attach

* 多重 attach，每個使用者會同時得到同一個 ``session`` 的控制權 ::

    $ screen -x [id | session_name]

hot keys
========

* ``screen`` 的 hot key 預設都以 ``^a`` 為前綴

* 把當前 ``screen`` detach ::

    ^a d

* 新增一個 ``window`` ::

    ^a c

* 切換回上一個 ``window`` ::

    ^a ^a

* 切換到下/上一個 ``window`` ::

    ^a n/p

* 切換到編號 k 的 ``window`` ::

    ^a [0123456789]

* 進入指令模式 ::

    ^a A

* 砍掉當前的 ``window`` ::

    ^a k

* 複製之前的文字 ::

    ^a ^[

  - 貼上 ::

      ^a ^]

screen 內部指令
===============

* 按下 ``^a A`` 後會進入指令模式

* 把該 ``window`` 的標題文字換成 ``test`` ::

    :title test

* 重新讀取設定檔 ::

    :source ~/.screenrc

* 更改 ``windows`` 的順序 ::

    :number <number>

  - 若 ``<number>`` 已經存在，則目前的 window 會和對方交換號碼以及位置

設定
====

* 個人設定檔

  - ``~/.screenrc``

* 顏色設定

  - ``man screen``
  - 搜尋 ``STRING ESCAPE``
  - ``caption always "%{WK}%-w%<%{=B GK}%n %t%{= KW}%+w%=%H%=%Y/%m/%d %c:%s"``
  - ``hardstatus alwaysignore``

* 按鍵設定

  - for ``Cygwin`` ``mintty`` ``xterm``

    + ``bindkey \033[1;5D prev # ctrl+left``
    + ``bindkey \033[1;5C prev # ctrl+right``

* 編碼

  - ``defutf8 on``
  - ``defencoding utf8``
