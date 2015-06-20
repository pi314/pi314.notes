==================
g++ & gdb 用法筆記
==================

g++
----

* 產生 Preprocessor 處理過的檔案 ::

    $ g++ -E {file}

* 產生組語 ::

    $ g++ -S {file}

* 產生 Object File ::

    $ g++ -c {file}

gdb
----

* 在編譯時需要加參數 ``-g`` 附上輔助用的訊息
* 執行 ::

    $ gdb a.out

  - 在第 100 行設定中斷點 ::

      gdb> b 100

  - 開始執行 ::

      gdb> r

  - 離開 ::

      gdb> q

  - 印出 backtrace ::

      gdb> backtrace

