============
= Mac OS X =
============

- 停用 spotlight ::

    sudo launchctl unload -w /System/Library/LaunchDaemons/com.apple.metadata.mds.plist

- 讓長按鍵盤重覆輸入按鍵 (有些應用程式長按鍵盤會有特殊符號選單) ::

    defaults write -機ApplePressAndHoldEnabled -bool false

MacPorts
--------

請參考 ``ports.rst``
