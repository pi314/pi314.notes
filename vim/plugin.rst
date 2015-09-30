===================
= Vim Plugin 筆記 =
===================

vim 設定檔目錄結構
------------------

::

  ~/.vim/
  ├── bundle/
  ├── filetype.vim
  ├── ftplugin/
  ├── plugin/
  ├── syntax/
  └── vimrc

* ``bundle``

  - 包含你所安裝的 plugin

* ``filetype.vim``

  - 定義各種你需要的 filetype，例如 ::

      au BufNewFile,BufRead *.todo setf todo

    + 定義了一個 filetype ``todo``

* ``ftplugin/``

  - 定義每個 filetype 的 key map，例如 filetype ``todo`` 的 map 就放在 ``todo.vim`` 裡面

* ``plugin/``

  - 放置其他會被載入的 vim script

* ``syntax/``

  - 定義每個 filetype 的語法上色，例如 filetype ``todo`` 的語法上色就定義在 ``todo.vim`` 裡面

* ``vimrc``

  - 和 ``~/.vimrc`` 相同用途

自己寫 Plugin
-------------

現在已經有很成熟的套件管理系統可以使用，例如 Vundle_

..  _Vundle: https://github.com/gmarik/Vundle.vim

把你的 Plugin 設計成這樣的目錄結構:

::

  plugin-folder/
  ├── filetype.vim
  ├── autoload/
  │   └── pluginname.vim
  ├── ftplugin/
  │   └── pluginname.vim
  ├── syntax/
  │   └── pluginname.vim
  ├── README.rst
  └── LICENSE

* ``ftplugin/`` 裡面只放初始化以及 mappings
* ``autoload/`` 裡面放所有的邏輯

  - 內部使用的 function 用 ``s:func`` 命名，使其 scope 限定在該 script 內
  - 開放給使用者的 function 用 ``pluginname#func`` 命名，使其能夠被 autoload

編寫 vim 語法上色檔
-------------------

* 基本方法 ::

    syn match {name} {regex}
    hi def {name} {argument}

  - ``name`` 為該語法定義一個名稱
  - ``regex`` 用來定義語法的內容，如果 ``regex`` 的結尾是空格，可能會讓下一個語法區塊出問題
  - ``argument`` 定義該語法的外觀，例如 ::

      hi def agdaXXX cterm=bold,underline ctermfg=1

    + 前景和背景需分別設定，若 terminal 支援的話也可以使用底線或其他功能
    + ``ctermfg`` 和 ``ctermbg`` 都可以使用顏色的名稱或是顏色的代碼

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

    + 要注意 ``cyan`` 指的其實是亮色 (不論是否有 ``cterm=bold``)， ``darkcyan`` 才是暗色

* 要注意 ``syntax on`` 會讓 vim 提早載入 ``syntax/`` 裡的 script，若有需要用到 ``ftplugin/`` 定義的變數，請用一些變數辨認狀態

其他
----

* ``setlocal noswapfile`` 和 ``setlocal buftype=nofile`` 可以產生免洗的視窗，用來顯示資訊
