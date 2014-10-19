=========
= Ports =
=========

FreeBSD Ports
-------------

- 第一次下載 port tree（``/usr/ports`` 不存在） ::

    portsnap fetch extract update

- 指定主機 ::

    portsnap -s portsnap.freebsd.org fetch extract

- 更新 port tree ::

    portsnap fetch update

- 務必檢查 ``/usr/ports/UPDATING`` 裡面的資訊 ::

    pkg updating

- 檢查已安裝的 ports 中有沒有新版本 ::

    portmaster -L | grep avail

- 更新一個或多個 ports ::

    portmaster -dyB editors/vim lang/python

- 直接更新所有已安裝的 ports

  - ``portmaster`` ::

      portmaster -dyBa

    1. ``-d``: 不要保留 dist file
    2. ``-y``: 全部用預設選項
    3. ``-B``: 不要備份舊的 ports
    4. ``-a``: 全部更新
  
  - ``pkg upgrade``

Mac OS X Ports
--------------

- 刪除沒被 reference 到的 ports ::

    port uninstall leaves

  + 需要不斷重覆執行，因為每清理一次就會有別的 ports 變成 leave
  + 建議在更新前先清理乾淨，避免卡在一些其實沒有用到的軟體

- 更新 port tree ::

    port selfupdate

- 更新 ports ::

    port upgrade outdated

- 搜尋 ::

    port search <package_name>

- 安裝 ::

    port install <package_name>
