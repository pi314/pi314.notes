==============
= Vim Plugin =
==============

一般的 vim 設定檔 layout
------------------------

::

  ~/.vim/
    bundle/
    filetype.vim
    ftplugin/
    plugin/
    syntax/
    vimrc

- ``bundle``

  包含你所安裝的 plugin
    
- ``filetype.vim``

  定義各種你需要的 filetype，例如 ::

    au BufNewFile,BufRead *.todo       setf todo

  定義了一個 filetype ``todo``

- ``ftplugin/``

  定義每個 filetype 的 key map，例如 filetype ``todo`` 的 map 就放在 ``todo.vim`` 裡面

- ``plugin/``

  放置其他會被載入的 vim script

- ``syntax/``

  定義每個 filetype 的語法上色，例如 filetype ``todo`` 的語法上色就定義在 ``todo.vim`` 裡面

- ``vimrc``

  和 ``~/.vimrc`` 相同用途
