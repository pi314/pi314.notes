=====
Ports
=====

FreeBSD Ports
-------------

- 更新 port tree
  - portsnap fetch update

- 務必檢查 /usr/ports/UPDATING 裡面的資訊
  - pkg updating

- 直接更新所有已安裝的 ports
  - portmaster -dyBa
  - d: 不要保留 dist file
  - y: 全部用預設選項
  - B: 不要備份舊的 ports
  - a: 全部更新

Mac OS X Ports
--------------

- 更新 port tree
  - port selfupdate

- 更新 ports
  - port upgrade outdated

