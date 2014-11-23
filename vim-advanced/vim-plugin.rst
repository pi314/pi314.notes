==============
= Vim Plugin =
==============

一般的 vim 設定檔 layout
========================

::

  ~/.vim/
    bundle/
    filetype.vim
    ftplugin/
    plugin/
    syntax/
    vimrc

* ``bundle``

  - 包含你所安裝的 plugin
    
* ``filetype.vim``

  - 定義各種你需要的 filetype，例如 ::

    au BufNewFile,BufRead *.todo       setf todo

  - 定義了一個 filetype ``todo``

* ``ftplugin/``

  - 定義每個 filetype 的 key map，例如 filetype ``todo`` 的 map 就放在 ``todo.vim`` 裡面

* ``plugin/``

  - 放置其他會被載入的 vim script

* ``syntax/``

  - 定義每個 filetype 的語法上色，例如 filetype ``todo`` 的語法上色就定義在 ``todo.vim`` 裡面

* ``vimrc``

  - 和 ``~/.vimrc`` 相同用途

編寫 vim 語法上色檔
===================

* 基本方法 ::
  
    syn match {name} {regex}
    hi def {name} {argument}

  - ``name`` 為該語法定義一個名稱
  - ``regex`` 用來定義語法的內容，如果 ``regex`` 的結尾是空格，可能會讓下一個語法區塊出問題
  - ``argument`` 定義該語法的外觀，例如 ::

      hi def agdaXXX cterm=bold,underline ctermfg=1

    - 前景和背景需分別設定，若 terminal 支援的話也可以使用底線或其他功能
    - ``ctermfg`` 和 ``ctermbg`` 都可以使用顏色的名稱或是顏色的代碼

    ===== ==== ================================
    NR-16 NR-8 COLOR NAME 
    ===== ==== ================================
    0     0    Black
    1     4    DarkBlue
    2     2    DarkGreen
    3     6    DarkCyan
    4     1    DarkRed
    5     5    DarkMagenta
    6     3    Brown, DarkYellow
    7     7    LightGray, LightGrey, Gray, Grey
    8     0*   DarkGray, DarkGrey
    9     4*   Blue, LightBlue
    10    2*   Green, LightGreen
    11    6*   Cyan, LightCyan
    12    1*   Red, LightRed
    13    5*   Magenta, LightMagenta
    14    3*   Yellow, LightYellow
    15    7*   White
    ===== ==== ================================

* Regex lookahead/behind

  - 可以 regex 中判斷前後文 (其實這已經超過 formal language 中 regex 的範圍了)

  - ``\@<=`` positive lookbehind
  - ``\@<!`` negative lookbehind
  - ``\@=`` positive lookahead
  - ``\@!`` negative lookahead

  - 範例

    - 比對所有的 ``foo`` ，但後面不能接 ``bar`` ::
      
        foo\(bar\)\@!

    - 比對所有的 ``bar`` ，但前面不能接 ``foo`` ::

        \(foo\)\@<!bar
