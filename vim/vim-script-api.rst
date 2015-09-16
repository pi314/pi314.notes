=========================
= Vim script 筆記 - API =
=========================

編輯區
------

* 取得編輯區內的內容

  - 取得游標所在的行的內容 ::

      getline('.')

  - 取得第 3 行的內容 ::

      getline(3)

* 游標位置

  - 取得游標所在的行數 ::

      line('.')

  - 取得游標的 column number ::

      col('.')

    + 1-based

  - 設定游標的位置 ::

      cursor({linenum}, {col})

* 設定游標所在行的內容 ::

    call setline('.', "abcd")

* 在游標所在行的下一行插入一行字串 ``xxx`` ::

    call append('.', "xxx")

切割
----

* 產生一個空空的 split ::

    new
    vnew

* 調整 split 的大小 ::

    resize 5
    vertical resize 30

Menu
----

* ``pumvisible()`` 回傳 "現在是否有 Menu 正在顯示"

  - 若 Menu 中只有一個選項，Menu 預設不會顯示，但 ``pumvisible()`` 仍會回傳 ``1``

指令
----

* 執行 normal mode 下的動作 ::

    normal! <movements>
