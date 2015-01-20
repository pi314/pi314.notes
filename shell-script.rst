============================
= shell script syntax note =
============================

bourne sh
---------

* 各種語法的開頭結尾

  - ``if`` - ``fi``
  - loop - ``do`` - ``done``
  - ``case`` - ``esac``

* ``if`` condition

  - 語法 ::

      if [ "$1" = "test" ]; then
        ...

      elif [ "$1" = "test2"]; then
        ...

      else
        ...

      fi

  - 檢查檔案是否存在 ::

      if [ -f file ]

  - 檢查目錄是否存在 ::

      if [ -d dir ]

* ``while`` loop

  - 語法 ::

      while [ 1 ]; do
        ...
      done

* ``for`` loop

  - 語法 ::

      for i in 1 2 3; do
        echo $i
      done

  - 對別的指令的 stdout 做遞代 ::

      str="1 2 3"
      for i in $str; do
        echo $i
      done

* ``case``

  - 語法 ::

      while [ $# -gt 0 ]; do
        case "$1" in
          -f) force_replace=1;;
          -a) echo "a"; echo "b";;
        esac
        shift
      done

* 把 local 變數傳給 subprocess

  - 把 ``var`` 變為自己的環境變數 (才可以傳給 subprocess) ::

      export var

  - 把 ``var`` 刪除 (同時也從環境變數中消失) ::

      unset var 

* 字串處理

  - 字串相等 ::

      if [ "$str" = "a" ]; then
      fi

  - 以下範例使用假設 ::

      #!/usr/local/env sh
      var=foodforthought.jpg

  - 切掉左邊

    + 從左邊開始尋找 ``fo`` ，找到最後一個 ``fo`` ，右邊剩下的字串為結果 (不包含比對到的 ``fo``) ::

        ${var##*fo}

    + 從左邊開始尋找 ``fo`` ，找到第一個 ``fo`` ，右邊剩下的字串為結果 (不包含比對到的 ``fo``) ::

        ${var#*fo}

  - 切掉右邊

    + 從右邊開始尋找 ``fo`` ，找到最後一個 ``fo`` ，左邊剩下的字串為結果 (不包含比對到的 ``fo``) ::

        ${var%%fo*}

    + 從右邊開始尋找 ``fo`` ，找到第一個 ``fo`` ，左邊剩下的字串為結果 (不包含比對到的 ``fo``) ::

        ${var%fo*}

  - slice

    + 取出第 1 到第 5 個字元，包含第 1 及第 5 個，字串從 0 開始算 ::

        ${var:1:5}

* IO重導向

  - 去除 stderr ::

      $ command 2>less

  - 去除 stdout，把 stderr 轉為 stdout ::

      $ command 2>&1 1>/dev/null

csh/tcsh
--------

* Global 的設定檔: ``/etc/csh.cshrc``

* ``alias`` 參數

  - ``\!:1`` 代表第一個參數，依此類推

  - ``alias t echo test_\!:1_test``

* 每次更新的 prompt ::

    alias precmd "`date`"
