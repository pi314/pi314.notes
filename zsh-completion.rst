================
Zsh 補完系統筆記
================
Zsh 的補完系統實在是太複雜，但是文件又幾乎沒有範例，我得做些筆記，不然一定會忘光

補完的指令不一定要存在，你可以為 ``hello`` 寫補完的函式，但是實際上沒有這個指令


基本
----
* 準備一個目錄，專門用來放 Completion Script，並且把目錄的路徑加入 ``fpath`` ::

    fpath=($HOME/.rcfiles/zsh/completions $fpath)

  - ``fpath`` 的型態是 Zsh Array，而不像 ``path`` 一樣是字串
  - 記得原本的 ``fpath`` 要留著，不然內建的都會讀不到

* 建立 ``_hello`` 檔案 ::

    #compdef hello
    _hello () {
        _arguments "*:argument:(arg1 arg2 XD)"
    }
    _hello "$@"

  - 現在輸入 ``hello `` 按下 ``tab`` 會補完


``_arguments`` 基本使用方式
---------------------------
``_arguments`` 的使用方法非常多，可以根據參數把選項做成選單

* 單字類的補完

  - 格式： ``TAG:DESCRIPTION:ACTION``
  - ``TAG`` 可以是參數的位置，例如 ``1`` 代表這個項目在第 1 個參數使用。 ``*`` 則是每個位置都套用
  - ``DESCRIPTION`` 目前不確定用途，但不能是空字串，可以是一個空白字元
  - ``ACTION``

    + 補完一些單字： ``(France Germany Italy)``
    + 補完一些單字，並附上說明： ``((Paris\:France Berlin\:Germany Rome\:Italy))``
    + 更多用法見 ``ACTION`` 章節

  - 範例 ::

      _arguments '*:Countries:((Paris\:France Berlin\:Germany Rome\:Italy))'

    + 會產生如下的選單 ::

        Berlin  -- Germany
        Paris   -- France
        Rome    -- Italy

* ``-`` 開頭的選項

  - 格式： ``-OPT[DESCRIPTION]``
  - 格式： ``-OPT[DESCRIPTION]:MESSAGE:ACTION``
  - ``-OPT`` 為補完選項
  - ``DESCRIPTION`` 為說明文字
  - ``MESSAGE`` 目前不確定用途
  - ``ACTION`` 如上述
  - 範例 ::

      _arguments\
        '-s[short output]'\
        '--l[long output]'\
        '-f[input file]:filename:_files'\
        '*:filename:{_files}'

    + ``-o[text]`` 會產生 ``-o`` 的補完，附上它的說明
    + ``-o[text]:message:action`` 會產生 ``-o`` 的補完、說明。若 ``-o`` 被選到了，下一個參數會使用 ``action`` 來補完
    + 預設選項 ``*:filename:{_files}`` 會以檔名做補完

  - 若有多個選項需要分享同一個說明 ::

      _arguments {-f,--force}'[description]'


``_alternative`` 基本使用方式
------------------------------
當你想要把兩個函式的結果都拿來補完的時候，可以用 ``_alternative`` 達成 ::

  _alternative \
    'users:user:_users' \
    'hosts:host:_hosts'


``ACTION``
----------
``ACTION`` 定義一個參數實際被補完時的行為

* 補完一些單字： ``(France Germany Italy)``
* 補完一些單字，並附上說明： ``((Paris\:France Berlin\:Germany Rome\:Italy))``
* 使用函式產生補完選項： ``func_name``

  - ``func_name`` 需要能夠產生補完，無法用 stdout 傳回結果
  - 補完檔案名稱： ``_file``
  - 補完以逗點分隔的選項：見 ``_values``
  - 目前不確定用 ``{`` ``}`` 包起來與否的差異
  - 若遇到不容易在一行內嵌入的狀況，分離成另一個函式應該可以解決

* 不補完，但改變狀態，以後可根據狀態做不同的補完： ``->state1``

  - 狀態會被存在 ``$state`` 變數中


``_values``
~~~~~~~~~~~
* 補完以逗點分隔的選項： ``{_values -s , dicts urban yahoo all moe}``

  - ``dicts`` 是說明而不是選項之一

* 補完 ``foo@news:woo`` 格式的字串： ``_sep_parts '(foo bar)' @ '(news ftp)' : '(woo laa)'``


感謝這份淺顯易懂的說明
----------------------
https://github.com/zsh-users/zsh-completions/blob/master/zsh-completions-howto.org
