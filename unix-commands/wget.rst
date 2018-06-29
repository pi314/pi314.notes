========
``wget``
========
* 範例 ::

    $ wget --recursive --no-clobber --page-requisites --html-extension --convert-links --restrict-file-names=windows --domains website.org --no-parent HTTP://URL

  - ``--recursive``: 下載整個網站
  - ``--domains website.org``: 只備份 ``website.org`` 內的網頁
  - ``--no-parent``: 不往上層目錄備份
  - ``--page-requisites``: 把 image 和 CSS 等資料也備份
  - ``--html-extension``: 副檔名設為 .html
  - ``--convert-links``: 把 link 改寫為相對路徑
  - ``--restrict-file-names=windows``: 必要時修改檔名
  - ``--no-clobber``: 不覆寫舊檔
  - ``--user``: htaccess 使用者名稱
  - ``--password``: htaccess 密碼
  - ``--no-check-certificate``: 不檢查憑證

* ``-P prefix``: 把所有下載的檔案存入 ``prefix/`` 目錄中
* 一次下載多個檔案

  - 直接抓 ::

      wget <url1> <url2> <url3> ...

    + 這個方法有機會使用 shell 的 expansion，一次展開一個範圍

  - 若 URL 們已經存在 ``urls.txt`` 裡面 ::

      wget -i urls.txt
