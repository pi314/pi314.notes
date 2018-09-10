===============================================================================
expect
===============================================================================
``expect`` 可以模仿使用者的按鍵操作，甚至根據畫面的內容執行不同的動作

Shebang ::

  #!/usr/bin/env expect -f


spawn
-------------------------------------------------------------------------------
``spawn <command>``

執行 ``<command>`` 指令，準備開始送按鍵給該 process


``interact``
-------------------------------------------------------------------------------
* 將控制權交給使用者
* 根據 man page， ``interact`` 期間還是可以對使用者的按鍵進行攔截，不過還沒研究


``expect``
-------------------------------------------------------------------------------
* 等待特定字串::

    expect "字串"

  - 等待 ``字串`` 出現在 output 中

* 等待多種字串::

    expect {
        "apple" {
            puts "found an apple\n"; exp_continue
        }
        "banana" {
            puts "banana\n"
            exp_continue
        }
        "press enter" {
            send "\r"
        }
    }

  - Output 出現 ``apple`` 或 ``banana`` 時，都用 ``exp_continue`` 讓該 ``expect`` 繼續執行
  - 直到 ``press enter`` 出現，送出一個 enter 鍵以後離開該 ``expect`` block

* 擷取畫面內容::

    expect -re "start (.*) end"
    puts "$expect_out(1,string)"

  - ``expect`` 加上 ``-re`` 參數後可以用 regex 做 matching
  - ``$expect_out`` 可以存 group 1 ~ 9
  - **注意** ``expect_out(1,string)`` 是 **連在一起的**

    + 逗點後面不可以加空白： ``$expect_out(1, string)``
    + 可以把它想成是變數名稱
