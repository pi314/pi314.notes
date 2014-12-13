============
= Mac OS X =
============

* 停用 spotlight ::

    # launchctl unload -w /System/Library/LaunchDaemons/com.apple.metadata.mds.plist

* 讓長按鍵盤重覆輸入按鍵 (有些應用程式長按鍵盤會有特殊符號選單) ::

    $ defaults write -g ApplePressAndHoldEnabled -bool false

* 投影機 (或是雙螢幕)

  - 先把 VGA 線接上轉接頭，再把轉接頭接上 Mac (據說順序很重要)

  - Command + F1 (不需按 Fn 鍵) 可以切換〔同步/延伸〕畫面

  - 系統偏好設定 > 顯示器 中可以調整螢幕的相對位置

MacPorts
--------

請參考 ``ports.rst``
