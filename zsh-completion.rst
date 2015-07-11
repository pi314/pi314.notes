================
Zsh 補完系統筆記
================

Zsh 的補完系統實在是太複雜，但是文件又幾乎沒有範例，我得做些筆記，不然一定會忘光

補完的指令不一定要存在，你可以為 ``hello`` 寫補完的函式，但是實際上沒有這個指令

基本
----

* 準備一個目錄，專門用來放 Completion Script，把目錄的路徑加入 ``fpath`` ::

    fpath=($HOME/.rcfiles/zsh/completions $fpath)

  - ``fpath`` 的型態是 Zsh Array，而不像 ``path`` 一樣是字串
  - 記得原本的 ``fpath`` 要留著，不然內建的都會讀不到

* 建立 ``_hello`` 檔案 ::

    #compdef hello
    _hello () {
        _arguments "*:argument:(a b c)"
    }
    _hello "$@"

  - 現在輸入 ``hello`` 按下 ``tab`` 會補完

``_arguments``
--------------

``_arguments`` 可能是最常被使用的功能之一，可以根據參數把選項做成選單

使用方法非常多

* 單純的選單 ::

    _arguments '1:Countries:(France Germany Italy)'

  - ``1`` 代表參數的位置，調整這個值可以在不同的參數補完不同的內容

    + ``*`` 代表全部套用

  - ``Countries`` 目前不知道它的作用，但不能是空字串，可以是一個空白字元
  - ``(France Germany Italy)`` 是補完的選項， ``()`` 是 Zsh Array

* 附有說明的選單 ::

    _arguments '*:Cities:((Paris\:France Berlin\:Germany Rome\:Italy))'

  - ``(())`` 裡面裝著一連串的 Pair，Pair 之間用 ``:`` 分開，但 ``:`` 需要跳脫，故最後變成 ``\:``
  - 會產生如下的選單 ::

      Berlin  -- Germany
      Paris   -- France
      Rome    -- Italy

* 附有說明的選單，且附有預設選項 ::

    local t
    t=(
      '-a[argument a]'
      '-b[argument b]'
      '*:filename:_files'
    )
    _arguments -s $t

  - ``t`` 是 Zsh Array，格式如上，一定要有一個預設選項 ``*`` ，且一定要是 Zsh 的補完指令

Context
-------

Zsh 可以根據不同的參數位置產生不同的補完選項

建立以下檔案 ::

  #compdef hello
  _hello () {
      local curcontext="$curcontext" state line
      typeset -A opt_args

      _arguments \
          '1: :->country'\
          '*: :->city'

      case $state in
      country)
          _arguments '1:Countries:(France Germany Italy)'
      ;;
      *)
          case $words[2] in
          France)
              _arguments '*:Cities:((Paris:France Berlin:Germany Rome:Italy))'
          ;;
          Germany)
              compadd "$@" Berlin Munich Dresden
          ;;
          Italy)
              local t
              t=(
                  '-aa[argument a]'
                  '-ab[argument b]'
                  '*:filename: _files'
              )
              _arguments -s $t
          ;;
          *)
              _files
          esac
      esac
  }
  _hello "$@"

* ``_arguments '1: :->country' '*: :->city'`` 可以為每個參數設定狀態，並存在 ``state`` 中，後面根據 ``state`` 的值就可以知道現在正在補完第幾個參數

* ``words`` 是 Zsh Array，會存放目前 Command Line 上的每個「字」
* ``compadd`` 目前還不確定用途，也是補完用的指令

compadd
-------

目前還不確定 ``compadd`` 和 ``_arguments`` 的設計差異，但感覺 ``compadd`` 是比較高階的控制

* 把一些單字加入接下來的補完選單 ::

    ``compadd opt1 opt2 opt3``

  - 如果想要補的選項有 ``-`` 開頭，可以用 ``compadd -- -a --long-option``

* 加上顯示一行說明文字 ::

    ``compadd -X 'explanation' opt1 opt2 opt3``

  - ``explanation`` 會顯示在補完選單的上方

