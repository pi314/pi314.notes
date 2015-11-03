==============
輸入輸出重導向
==============
在 UNIX-like 環境中工作時，很常會遇到輸入輸出重導向的問題

POSIX 定義了（其實可能更早出現，這裡暫時不探究歷史）輸入輸出的模型：

* Stdin：做為程式的輸入，編號為 0
* Stdout：做為程式的輸出，編號為 1
* Stderr：做為程式錯誤訊息的輸出，編號為 2

所以當你寫了一支程式，可以從 stdin 抓資料、在 stdout 上印出訊息、在 stderr 上印出錯誤訊息

可以想像有三個 channel，資料可以丟進不同的 channel，代表不同的意義

這些 channel 被稱為串流（stream）


Pipe
-----
Pipe line 是使用重導向的一個例子，考慮以下指令 ::

  ls -1 | nl

對 ``ls -1`` 來說，它把檔案列表顯示在螢幕上，但其實這些字被作業系統攔截了下來，轉送給 ``nl``

對 ``nl`` 來說，它從鍵盤抓取一行一行的字，加上行號並輸出到螢幕上，但其實這些輸入都不是從鍵盤來的


``$()``
--------
編寫 Shell Script 時，可以把指令的結果抓取起來，存到變數裡面 ::

  value=$(date)

``date`` 指令打算把日期印在螢幕上，但這些字被攔截下來，做成字串，存進 ``value`` 變數中


合併 Stdin/Stdout
------------------
準備以下範例程式 ::

  # test.py
  import sys
  print('stdout', file=sys.stdout)
  print('stderr', file=sys.stderr)

執行 ::

  $ python3 test.py
  OUT
  ERR

會印出兩串字在螢幕上，但其實 ``OUT`` 是印在 stdout，而 ``ERR`` 是印在 ``stderr`` 上的

可以用 ``less`` 指令檢查 ::

  $ python3 test.py | less

執行後按下 Ctrl + L，將會只剩下 ``OUT`` 在螢幕上，可以得知 pipe 只導出 stdout

執行 ::

  $ python3 test.py 2>&1

將會把 ``OUT`` 和 ``ERR`` 都導入 stdout

``&1`` 代表 stdout， ``2>&1`` 把 stderr 的內容導入 stdout


分離 Stdin/Stdout
------------------
執行 ::

  $ python3 test.py 1>out.txt 2>err.txt

將會把 ``OUT`` 和 ``ERR`` 分別寫入 ``out.txt`` 以及 ``err.txt``


交換 Stdin/Stdout
------------------
執行 ::

  $ python3 test.py 3>&2 2>&1 1>&3

將會使 stdout 和 stderr 的內容交換

看起來其實滿難懂的

我的理解是，程式把字丟入 stdout，和字實際上在哪裡出現是兩回事，所以對我來說 stdout 和 stderr 都要分成兩邊 ::

  python3 test.py --+-- [stdout] ---- [stdout]
                    |
                    '-- [stderr] ---- [stderr]

左邊是程式看到的，右邊才是實際上被丟到的地方

接下來一步一步解釋發生了什麼事

1.  ``python3 test.py`` ::

      python3 test.py --+-- [stdout] ---- [stdout]
                        |
                        |
                        '-- [stderr] ---- [stderr]

2.  ``3>&2`` 把 ``3`` 導入 stderr ::

      python3 test.py --+-- [stdout] ---- [stdout]
                        |
                        |
                        +-- [stderr] ---- [stderr]
                        |                       ^
                        |                       |
                        '-- [3] --------- [3] --'

    + 實際上 ``3`` 這個串流原本是不存在的，不過我指定要把它導到 stderr，系統只好在左邊生出一個 ``3``
    + 由於左邊生出了 ``3`` ，右邊也要生出 ``3``

3.  ``2>&1`` 把 stderr 導入 stdout ::

      python3 test.py --+-- [stdout] ---- [stdout]
                        |                     ^
                        |                     |
                        |              .------'
                        +-- [stderr] -'
                        |                 [stderr]
                        |                       ^
                        |                       |
                        '-- [3] --------- [3] --'

4.  ``1>&3`` 把 stdout 導入 ``3`` ::

      python3 test.py --+-- [stdout] --.  [stdout]
                        |               '.    ^
                        |                 '.  |
                        |              .---|--'
                        +-- [stderr] -'    |
                        |                  v
                        |                 [stderr]
                        |                       ^
                        |                       |
                        '-- [3] --------- [3] --'

做了這些操作以後，stdout 和 stderr 就會互換了
