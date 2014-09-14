============
= Vim 筆記 =
============

CLI 參數
========

-   vim file1 file2 file3
    -   用 buffer 模式開啟

-   vim -p file1 file2 file3
    -   用 tab 模式開啟
    -   有數量上限，約為 10 頁，超出的檔案不會被開啟

-   vim -M
    -   用唯讀模式開啟

-   sudoedit
    -   sudoedit 是 sudo 和 vim 的組合，會將檔案先複製到 /tmp 下，在 :wq 以後再用 root 權限置換掉原本的檔案
    -   只有第一個分頁有 sudo 效果

快捷鍵們
========

移動
----

-   移游標

    -   gg
        -   回到檔首

    -   G
        -   跳到檔尾

    -   H / L / M
        -   頁首 / 頁尾 / 中間

    -   <C-f> / <C-b>
        -   一次翻動一頁

    -   <C-u> / <C-d>
        -   一次翻動半頁

    -   ^ / $
        -   行首行尾

    -   f_
        -   向右尋找，把游標移到 C 字元處
        -   FC 會向左尋找

    -   <C-o>
        -   跳回游標的上一個位置，可在檔案之間跳躍

-   移畫面

    -   <C-e> / <C-y>
        -   只移畫面，類似設多文字編輯器的 Ctrl+UP, Ctrl+Down

    -   zz / zt / zb
        -   把游標所在的行移到畫面中間 / 上面 / 下面

-   貼上

    -   <C-r>_
        -   在 insert/replace/command mode 下，從 register _ 貼上內容

    -   "0p
        -   指定從 yank 的 register 貼上

    -   在多行前面貼上同樣的字串
        -   先用 <C-v> 選取一整條，大寫 I，<C-r>0

-   反白
    
    -   v
        -   以字元為單位反白

    -   V
        -   以行為單位反白

    -   <C-v>
        -   區塊反白
        -   o / O 可以在區塊反白時讓游標跳到反白區的對面

    -   反白後按下 u / U
        -   把反白內容轉為 小/大 寫

-   刪除
    
    -   dt_
        -   從游標開始刪除直到 _ 為止，_ 不被刪除

    -   df_
        -   從游標開始刪除，刪到 _ 為止，_ 也被刪除

    -   d3j
        -   從游標開始從下刪 3 行，共刪四行

    -   di[
        -   刪除 [] 內的字，{} () <> "" '' 都可用
        -   diB == di{
        -   dib == di(

    -   dit
        -   刪除一個 html tag 內的字
    
    -   D
        -   從游標刪到行尾，游標處的字也刪除

    -   C
    -   從游標刪到行尾，並進入 insert mode

    -   cc / S
        -   把整行清除，留下一空行，並進入 insert mode

    -   di[vhp
        -   把 [abc] 變成 abc
        -   "刪除" 的把內容放入 ""，也是貼上的預設來源

    -   [insert mode] <C-w>
        -   和許多 shell CLI 的行為一樣，從游標開始往回刪除一個單字，最後維持在 insert mode
        -   <C-u> 也可用

-   書籤

    -   ma
        -   在游標所在行設定書籤 a

    -   'a
        -   跳至書籤 a

-   分頁

    -   :tab ball
        -   把 buffer 轉為 tab

    -   <C-w>T
        -   把 split 轉為 tab

    -   :tabm +1
        -   把 tab 往下一個位子移動

    -   :Vex
        -   Vertical splite 的檔案總管
        -   在中文環境下有問題

    -   gt / :tabn
        -   跳到下一個分頁

    -   gT / :tabp
        -   跳到上一個分頁

    -   :tabdo COMMAND
        -   對每一個 tab 都執行 COMMAND

    -   :Tex
        -   分頁模式的檔案總管

-   Split

    -   :vertical resize 50
        -   把 split 視窗寬度設定為 50 字元
        -   也可使用 +50 來增加 50 字元

    -   <C-w>H / J / K / L
        -   移動 split 視窗的位置

    -   :set mouse=a
        -   可以用滑鼠調整視窗大小

-   Buffer

    -   :bn
        -   下一個檔案

    -   :bp
        -   上一個檔案

    -   :bw
        -   關掉檔案

    -   :args
        -   查看開啟 vim 時傳入的 CLI 參數

    -   :set autowrite, :bufdo COMMAND
        -   設定「自動存檔」，再對每一個 buffer 都執行 COMMAND
        -   自動存檔是指在切換 buffer 時會自動存檔
        -   需要自動存檔是因為切換 buffer 時需要先存檔, 不設定的話就不能對每個 buffer 執行指令

-   外部指令

    -   :% !sort
        -   把當前內容用 sort 指令處理過，再直接取代現在的內容

    -   :w !python
        -   把當前內容 pipe 給 python

    -   [normal] !!COMMAND
        -   用指令的結果取代該行內容

    -   :r !cal
        -   在游標所在下一行插入指令結果

    -   [visual] !COMMAND
        -   把反白的區域當成 stdin 送給外部指令，並把結果取代掉反白區

-   取代

    -   :%s/^/\=(1 - line("'<") + line(".")) . "\. "/

-   Register

    -   "*
        -   系統剪貼簿（目前只在 Cygwin 測試成功

    -   "0
        -   複製的預設剪貼簿

    -   ""
        -   刪除 / 剪下的預設剪貼簿

-   其他

    -   <C-[>
        -   等於 ESC 鍵

    -   <C-v><TAB>
        -   插入 tab 字元，有些設定會讓 tab 字元在輸入時直接置換成空格

    -   gf
        -   Go File，以游標所在的字串為標名開啟檔案
        -   :bf
            -   跳回原檔

        -   <C-o>
            -   跳到 "上一個位置"

        -   <C-w>gf
            -   在新分頁中開檔

    -   vim scp://pi314@HOST/FILE
        -   讓 vim 以 scp 方式抓取遠端檔案，如果不用 scp 而是用 sftp 或 ftp 的話需打出絕對路徑

    -   :TOhtml
        -   把目前的畫面做成 html file

    -   "ayy
        -   把該行複製進 "a register 中

    -   :noh
        -   把本次搜尋的上色清除，但 search pattern 仍存在，故按下 n 還是可以繼續搜尋

    -   vim 的 regex
        -   () 如果不 escape，就視為普通的括號
        -   [] 需要 escape 才會是普通的括號
        -   \<abc\> 可以只比對到 abc 單字，不會 match aabcc 中間的 abc
            -   是 [normal] # 預設的行為

    -   移除檔首的 BOM
        -   :set nobomb

    -   [insert][replace] <C-o>
        -   暫時回到 normal mode，按一個按鍵後即回到 insert mode

    -   :set

        -   列出一些設定

        -   :set ff=unix
            -   修改檔案格式為 unix

    -   :retab
        -   把檔案中的 tab 都置換成適合長度的 space

    -   :nnoremap k gk
        -   在太長斷行的字串上垂直移動

-   特殊設定

    -   對每個檔案套用不同的縮排寬度
        -   autocmd FileType html serlocal shiftwidth=2 tabstop=2
        -   autocmd FileType make setlocal noexpandtab
