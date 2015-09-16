==========================
= Vim script 筆記 - VimL =
==========================

變數
----

* 變數沒有型態，且為弱型別（不同型態的資料運算時可能會自己轉型）

* 前綴

  - ``&``: 選項 (可以用 ``set`` 指令更改的那些)
  - ``l:``: Function 的區域變數
  - ``a:``: Function 參數
  - ``t:``: Tab 的區域變數
  - ``s:``: Script 的區域變數
  - ``b:``: Buffer 的區域變數
  - ``w:``: Window 的區域變數
  - ``g:``: 全域變數
  - ``v:``: vim 預設的變數

* 使用 ``let a = 3`` 來賦值

* 取得設定值 ::

    let a = &shiftwidth

語法
----

* if condition ::

    if <condition>
      ...
    elseif <condition>
      ...
    endif

* while loop ::

    while <condition>
      ...
    endwhile

* for loop ::

    for i in list
      ...
    endfor

* function definition ::

    function! <function-name> (<arguments>)
      ...
    endfunction

  - ``function!`` 代表要 override 同名的 function

字串
----

* 字串比對

  - 避免使用 ``==`` ，因為其 case sensitive 設定是根據 set ignorecase
  - case sensitive 的字串比對 ``==#``
  - case insensitive 的字串比對 ``==?``
  - 字串 regex match

    + case sensitive ``=~#``
    + case insensitive ``=~?``
    + pattern 建議用單引號字串

* 字串長度 (bytes) ::

    strlen(s)

* 字串 "外觀" 長度 ::

    strdisplaywidth(s)

* 重複字串 ::

    repeat('-', 80)

* 切片

  - slice ::

      "abcdefg"[1:3] ==# "bcd"

    + 和 Python 不同，Vim 的 slice 是上下都包含的

  - strpart ::

      strpart("abcdefg", 3, 2)    == "de"
      strpart("abcdefg", -2, 4)   == "ab"
      strpart("abcdefg", 5, 4)    == "fg"
      strpart("abcdefg", 3)       == "defg"

內建函式
--------

* 長度 ::

    len(list_a)

* ``complete({start-col}, {match})``

  - ``complete`` 函式只能在 insert mode 被呼叫，會產生一個選單，列出 ``{match}`` 裡的選項
  - 該行會從 ``{start-col}`` 開始被切除，直到游標所在的位置為止，替換成 ``{match}`` 裡的選項
  - ``{start-col}`` 是 1-based

* ``exists()``

  - 檢查某變數/函式/選項是否存在，見 ``:help exists``

其他
----

* 不定數量參數的函式 ::

    function! foo(arg1, ...)
    endfunction

  - ``a:0`` 代表 ``...`` 的參數數量
  - ``a:1`` 為 ``...`` 的第一個參數
