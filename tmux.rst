===================
= tmux 用法及設定 =
===================

用詞
====

``tmux`` 的介面可以分成

i.  類似 ``screen`` 的分頁
ii. 分割畫面的區塊

這份文件統一稱呼

i.  類似 ``screen`` 的分頁: window
ii. 分割畫面的區塊: pane

command line arguments
======================

- 開啟一個新的 ``tmux`` session ::

    tmux

- 列出目前的 ``tmux`` session ::

    tmux ls

hot keys
========

- ``tmux`` 的 hot key 預設都以 ``^b`` 為前綴

- hot keys 可參考 http://www.dayid.org/os/notes/tm.html

- 類 ``screen`` 功能

  - 新增一個 ``window`` ::

      ^b c

  - 切換至下/上一個 ``window`` ::

      ^b n/p

  - 進入指令模式 ::

      ^b :

  - 重新命名當前的 ``window`` ::

      ^b ,

    - 與指令 ``rename-window`` 相同，可在 ``^b ?`` 中查詢到

  - 進入 copy mode，按下 enter 離開 ::

      ^b [

- ``pane`` 功能

  - 在當前 ``window`` 中新增一個水平分割的 ``pane`` ::

      ^b "

  - 在當前 ``window`` 中新增一個垂直分割的 ``pane`` ::

      ^b %

  - 切換至另一個 ``pane`` ::

      ^b o

  - 把當前 ``window`` 中的 ``pane`` 位置旋轉 ::

      ^b ^o

  - 把當前的 ``pane`` 分出去變成 ``window`` ::

      ^b !

  - 調整 ``pane`` 的大小 ::

      ^b ^(arrow key)

- 其他

  - 列出 key bindings ::

      ^b ?

    - 按下 enter 後離開

tmux 內部指令
=============

- ``:detach``

- 把當前的 ``window`` 重命名為 ``test`` ::

    :rename-window test

設定
====

- 個人設定檔: ``~/.tmux.conf``

- ``C-x`` 為 ``^x``

- ``M-x`` 為 ``meta+x`` (``alt+x``)

- 類 ``screen`` 設定 ::

    bind-key -n "C-Left"  select-window -t :-
    bind-key -n "C-Right" select-window -t :+
