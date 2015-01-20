========
指令筆記
========

* ``find``

  - 用各種條件尋找檔案

  - 從當前目錄開始尋找檔名開頭為 Kalafina 的項目 ::

      find . -name 'Kalafina*'

  - 當前目錄中包含的所有子目錄 ::

      find . -type d

  - 當前目錄中包含的所有子檔案 ::

      find . -type f

  - 過濾出權限為 755 的項目 ::

      find DIR -perm 755

  - 過濾出權限不是 755 的項目 ::

      find DIR ! -perm 755

  - 對於每一個項目，都執行後面的指令, ``{}`` 會被代換成檔名 ::

      find DIR -exec sh -c "...{}..."

    + 後面餵給 ``sh`` 的參數中, backtick 中的 ``{}`` 不會被代換 ::

        find DIR -exec sh -c "printf `stat -c %a {}`'text'" \; 會失敗

    + 需要改用 ``$()``, 並把 ``$`` escape 掉 ::

        find DIR -exec sh -c "printf \$(stat -c %a {})'text'" \;

  - 把 ``DIR`` 目錄裡的目錄權限設為 755 ::

      find . -type d ! -perm 755 -exec sh -c "chmod 755 '{}'" \;

  - 把 ``DIR`` 目錄裡的檔案權限設為 644 ::

      find . -type f ! -perm 644 -exec sh -c "chmod 644 '{}'" \;

* ``ls``

  - 按照修改時間排序, 最新的在最前面 ::

      ls -t

  - 每個項目一行 ::

      ls -1

  - 反向排序 ::

      ls -r

* ``getopt``

  - 過濾出有效的參數

  - ``getopt`` 和 ``getopts`` 不一樣, 據說 ``getopts`` 比較強, 但因為 bourne shell 沒有內建, 所以我選擇 ``getopt``

  - 基本用法
  
    + ``getopt OPTSTRING PARAMETERS``
    + ``OPTSTRING`` 格式
    
      * ``"abc,def:"``
      * ``def`` 參數會以 ``-d value`` 的形式被處理
      * ``abc`` 會以 ``-a`` 的形式被處理
      
    + ``PARAMETERS``

      * 待 parse 的字串

  - 例子 ::

      $ getopt f:abc -c -f file -ab
      -c -f file -a -b --

  - 在 Shell Script 中使用 ::

      #!/bin/sh
      set -- `getopt :f $@`
      force_replace=0
      while [ $# -gt 0 ]; do
          case "$1" in
              -f) force_replace=1;;
          esac
          shift
      done

    + 上述程式 parse ``"-f"`` 參數，若有該參數則 ``force_replace`` 被設為 1

* ``tar``

  - 壓縮 ::

      tar cvf dst src_dir

    + 把壓縮結果輸出到 ``stdout`` ::

        tar cvf - src_dir

  - 解壓縮 ::

      tar xvf src

    + 從 ``stdin`` 輸入並解壓縮 ::

        tar xvf -

    + 會在當前目錄展開

* ``nc``

  - listen ::

      nc -l {PORT}

  - send ::

      nc {IP} {PORT}

  - 嘗試連線, 但不送出資料 ::

      nc -zv {IP} {PORT}

  - 範例: ``nc`` as TCP proxy

    + From wiki: netcat ::

        #!/usr/bin/sh
        if [ -p "backpipe" ]; then
          echo "backpipe exists."
        else
          mkfifo backpipe
        fi

        if [ -z $1 ] || [ -z $2 ] || [ -z $3 ]; then
          echo "Usage: $0 port host port"
          exit
        fi

        while [ 1 ]; do
          echo "listening on port $1 and redirect to $2:$3"
          nc -l $1 0<backpipe | nc $2 $3 1>backpipe
          echo "one connection ends, start another."
        done

  - 有些機器需要加上 ``-N`` option 才能正常結束連線

    + 有些 FreeBSD 10 需要
    + Mac OS X 沒有這個 option

* ``ping``

  - 改變 ``ping`` 的 interval ::

      ping -i 0.1 x.x.x.x    # 每 0.1 秒 ping 一次

    + 0.2 秒以下需要 root permission

  - 指定從某個 interface 發出封包 ::

      ping -I wlan0 x.x.x.x

  - 發出 5 個封包後就停止 ::

      ping -c 5 x.x.x.x

  - flood ::

      ping -f localhost

    + 需要 root permission

  - 改變 ``ping`` 的封包大小 ::

      ping -s 100 x.x.x.x

    + 實際送出的封包會再加上 header 28 bytes

* ``nmap`` ::

    nmap -v {host}

* ``openssl``

  - 產生 hash ::

      openssl passwd -crypt -salt 5W 123456

  - 讓 CMD 的執行過程被 ``openssl`` 包裝 ::

      openssl s_client CMD

  - 傳輸檔案

    a.  接收端 ::

          nc -l 12345 | openssl enc -d -aes-256-cbc -nosalt | base64 -D | <data-receive>

    b.  發送端 ::

          <data-source> | base64 | openssl enc -e -aes-256-cbc -nosalt | nc localhost 12345

* ``df``

  - 檢查硬碟使用量以及剩下容量

  - 用 MB GB 等單位顯示 ::

      df -h

* ``dig``

  - 查詢 ``cs.nctu.edu.tw`` domain 的 ``mx`` record ::

      dig mx cs.nctu.edu.tw

    + ``mx``, ``A``, ``AAAA``, ``ns``, ``cname``, ``txt``, ``axfr``, ``soa`` 也都可以查詢

  - 指定向 DNS server ``140.113.1.1`` 查詢 ::

      dig @140.113.1.1 bsd1.cs.nctu.edu.tw

  - 指定向 DNS server ``140.113.1.1`` 查詢 ``mx`` record ::

      dig @140.113.1.1 mx cs.nctu.edu.tw

  - 查反解 ::

      dig -x 140.113.1.1

  - 在 FreeBSD 10 已被 ``drill`` 指令取代, 可到 ``dns/bind-tools`` ports 裡安裝

* ``nslookup``

  - 查詢 ``pi314.nctucs.net`` 的 IP ::

      nslookup pi314.nctucs.net

  - 指定向 DNS server ``140.113.1.1`` 查詢 ::

      nslookup pi314.nctucs.net 140.113.1.1

  - 查反解 ::

      nslookup 140.113.1.1

  - 在 FreeBSD 10 已被 ``drill`` 指令取代, 可到 ``dns/bind-tools`` ports 裡安裝

* ``host``

  - 查詢 ``pi314.nctucs.net`` 的 IP ::

      host pi314.nctucs.net

  - 指定向 DNS server ``140.113.1.1`` 查詢 ::

      host pi314.nctucs.net 140.113.1.1

  - 查反解 ::

      host 140.113.1.1

* ``dd``

  - 低階檔案輸出寫入工具

  - 指定輸出到 ``TARGET`` ::

      echo test | dd of=TARGET

    + ``TARGET`` 可以是檔案或是硬碟的 device file

  - 指定從 ``TARGET`` 輸入 ::

      dd if=TARGET

  - 刷新硬碟上的 bit（治療可復原的 bit error） ::

      dd if=/dev/da0 of=/dev/da0

  - 把硬碟資料透過網路傳送 ::

      dd if=/dev/da0 | nc BACKUP_SERVER PORT

* ``seq``

  - 產生 1 ~ 10 的數字作為 output ::

      seq 10

  - 使用在 shell script 中 ::

      for i in $(seq 10); do; echo $i; done

* ``nl``

  - 把 ``stdin`` 加上行號後輸出

* ``ln``

  - 製作軟連結 ::

      ln -s source link_name

* ``pushd``, ``popd``, ``dirs``

  - 操作 shell 中的目錄堆疊

  - 把目前目錄 push 到 stack，並 ``cd`` 到 ``dir`` ::

      pushd dir

  - 把 stack pop 掉一次，並 ``cd`` 回 stack 最上層的目錄 ::

      popd

  - 列出現在的 stack，左邊的是最上層的 ::

      dirs

* ``grep``

  - 列出含有 ``test`` 的行 ::

      cat file | grep "test"

  - 列出不含有 ``test`` 的行 ::

      cat file | grep -v "test"

  - 在當前目錄遞迴的尋找所有檔案中的 ``test`` 字串 ::

      grep -R "test" .

  - 只印出比對到的部份 ::

      grep -o "test"

  - 印出比對到的前後行

    + 印出比對到的前一行 ::

        grep -A 1

    + 印出比對到的後一行 ::

        grep -B 1

    + 印出比對到的前後一行 ::

        grep -C 1

* ``egrep``

  - 等於 ``grep -E`` ，使用擴充的 regex ::

      ls | egrep "mp4|avi"

* ``xargs``

  - 把前面的 output 當成 ``xargs`` 指令的 參數

  - 各系統的 ``xargs`` 實作不同 (參數也不同)

    + FreeBSD, GNU ::

        find . -type f | xargs -I% echo test%test

      * ``-I%`` 設定 ``stdin`` 的取代符號，並把 ``stdin`` 的每一行獨立餵給 ``echo``

  - 在 ``xargs`` 中使用 pipe（fork 出一個 ``sh`` 來執行） ::

      something | xargs -I% sh -c "echo % | nl"

* ``sh``

  - 印出實際上執行了什麼 ::

      sh -xc "something"

* ``date``

  - 顯示目前時間 ::

      date "+%Y/%m/%d %H:%M:%S"

  - 顯示時區 ::

      date "+%Z"

  - 修改日期 ::

      date -s 2005/10/10

  - 修改時間 ::

      date -s 22:10:30

* ``cp``

  - 備份 ::

      cp -nvr SRC DST

    + ``-n``: 不覆寫原檔
    + ``-v``: 列出所做的動作
    + ``-r``: recursive

* ``diff``

  - 比較兩個目錄的差異, 另有參數可以只比較檔案列表 ::

      diff -r DIR1 DIR2

* ``wget``

  - 範例 ::

      wget --recursive --no-clobber --page-requisites --html-extension --convert-links --restrict-file-names=windows --domains website.org --no-parent HTTP://URL

    + ``--recursive``

      * 下載整個網站

    + ``--domains website.org``

      * 只備份 ``website.org`` 內的網頁

    + ``--no-parent``

      * 不往上層目錄備份

    + ``--page-requisites``

      * 把 image 和 CSS 等資料也備份

    + ``--html-extension``

      * 副檔名設為 .html

    + ``--convert-links``

      * 把 link 改寫為相對路徑

    + ``--restrict-file-names=windows``

      * 必要時修改檔名

    + ``--no-clobber``

      * 不覆寫舊檔

* ``rename``

  - 把 ``{file}`` 中符合 ``{expressoin}`` 的部份換成 ``{replacement}`` ::

      rename {expression} {replacement} {file}

  - Example ::

      rename .htm .html *.html

* ``portsnap``

  - 請參考 ``ports.rst``

* ``zpool`` ::

    zpool status -v
    zpool export ZPOOL
    zpool import ZPOOL ZPOOLDD
    zpool status -v

* ``pfctl``

  - 在 ``{table}`` 裡增加 ``{IP}`` ::

      pfctl -t {table} -T add {IP}

  - 從 ``{table}`` 中刪除 ``{IP}`` ::

      pfctl -t {table} -T delete {IP}

  - 測試 ``{IP}`` 是否在 ``{table}`` 中 ::

      pfctl -t {table} -T test {IP}

  - 重新載入設定檔 ::

      pfctl -f /etc/pf.conf

* ``ftp``

  - 開啟 FreeBSD 內建的 ftp server ::

      /usr/libexec/ftpd -D -l -l

    + ``-D`` 讓 ftp 以 daemon 的方式啟動
    + ``-l -l`` 叫 ``syslogd`` 記錄每次的連線，用兩次 ``-l`` 則可以連使用的動作都記錄
    + ``-l`` 要留下連線記錄還需要配合修改 ``/etc/syslog.conf`` 才會啟動記錄

  - 指令列表（在連上 ftp server 後） ::

      ls
      cd
      less
      get remote-file {local-file}
      put local-file {remote-file}
      quit

* ``portmaster``

  - 請參考 ``ports.rst``

* ``portaudit``

  - 掃漏洞 ::

      portaudit

    + 漏洞在更新該 ports 後常常可以解決

  - 在 FreeBSD 10 後已被 ``pkg audit`` 取代

* ``pw``

  - 把一個 user 從一個 group 中刪除 (未測試) ::

      pw groupmod groupname -d userName

* ``ifconfig``

  - 把 ``em0`` interface 關掉 ::

      ifconfig em0 down

  - 把 ``em0`` interface 打開 ::

      ifconfig em0 up

  - 手動設定 IP 給 ``em0`` ::

      ifconfig em0 inet x.x.x.x netmask x.x.x.x

* ``tcpdump``

  - 指定 interface 並只聽取 ICMP 封包 ::

      # tcpdump -i <interface> icmp

  - dump 出可被 wireshark 開啟的格式 ::

      # tcpdump -i <interface> -s 65535 -w <some-file>

* ``rsync``

  - 參數格式 ::

      rsync options source destination

  - 取代 ``cp`` ，一樣是 copy 但是有進度條 ::

      rsync -ah --progress

  - 放棄 permission，owner，group ::

      rsync --no-p --no-o --no-g

  - 範例 ::

      rsync -arvzh --progress

    + ``-a``: archive mode，保留大部份資訊
    + ``-r``: recursive
    + ``-v``: verbose
    + ``-z``: 傳送時壓縮資料
    + ``-h``: 用人類好讀方式顯示資訊

* ``chmod``

  - 遞迴的把所有 exec bit "復原" ::

      chmod -R +r+X

    + Linux 和 FreeBSD 的 man page 寫得不太一樣

* ``command``

  - 檢查指令的實際位置

      ``command -v python3``

  - bourne shell 內建指令

  - 和 ``which`` 的差異

    + ``command`` 為內建指令，比較便宜，且行為比較能被確定
    + ``which`` 指令只用 ``stdout`` 做為結果，exit status 都是 ``0``

* ``mosh``

  - 開啟 Mosh Server ::

      mosh-server new -p port

  - Client 連上 Mosh Server ::

      $ export MOSH_KEY=<key>
      $ mosh-client <server-ip> <port>

* ``paste`` ::

    $ paste -d ' ' file1 file2


  - 把兩個檔案對齊 ``cat`` 出來

* 其他

  - 印出一個檔案，但前 5 行不要印出來 ::

      cat example.txt | awk '{ if(NR > 5) print $0;}'

    + 可以把 cat 改成用 nl 確認真的只有前 5 行沒有印出

  - 把目錄 ``DIR`` 從 A host 丟到 B host

    + A ::

        tar cvf - DIR | nc -l 12345

    + B ::

        nc {A's IP} 12345 > DIR.tar
        nc {A's IP} 12345 | tar xvf -

    + 如果 A 沒有 public IP 的話就改把 port 開在 B 上

  - 查看系統安裝的記憶體 ::

      grep memory /var/run/dmesg.boot

  - 把 ``rm`` ``alias`` 成 ``mv``, 不直接刪除檔案 ::

      alias rm 'mv \!* ~/.trash'

  - [``tcsh``] 把任意一個被 suspend 的 process 叫到 forground ::

      %[number]

  - 把漫畫檔名重新編為流水號

    + 假設檔案的修改時間是照實際順序的 ::

        ls -1tr |
        nl |
        awk '{print "mv " $2 " " $1 ".jpg"; }' |
        xargs -I% sh -c %

    + 想法

      * 先用 ``ls -1tr`` 依序列出檔名

      * 用 ``nl`` 產生流水號

      * 用 ``awk`` 輸出 ``mv origin.jpg {n}.jpg`` 的 shell script

      * 用 ``xargs`` 一行一行丟給 ``sh`` 執行

  - 可愛的小時鐘 ::

      while [ 1 ]; do clear; date +"%H:%M:%S" | figlet -m 4; sleep 1; done

