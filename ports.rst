=====
Ports
=====

FreeBSD Ports
-------------

- 第一次下載 port tree（/usr/ports 不存在） ::
    portsnap fetch extract update

- 指定主機 ::
    portsnap -s portsnap.freebsd.org fetch extract

- 更新 port tree ::
    portsnap fetch update

- 務必檢查 /usr/ports/UPDATING 裡面的資訊 ::
    pkg updating

- 檢查已安裝的 ports 中有沒有新版本 ::
    portmaster -L | grep avail

- 更新一個或多個 ports ::
    portmaster -dyB editors/vim lang/python

- 直接更新所有已安裝的 ports ::
    portmaster -dyBa
  - d: 不要保留 dist file
  - y: 全部用預設選項
  - B: 不要備份舊的 ports
  - a: 全部更新

Mac OS X Ports
--------------

- 更新 port tree ::
    port selfupdate

- 更新 ports ::
    port upgrade outdated

