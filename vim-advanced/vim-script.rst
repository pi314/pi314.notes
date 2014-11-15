===================
= Vim script 筆記 =
===================

變數
====

* 變數沒有型態，且為弱型別（不同型態的資料運算時可能會自己轉型）

* Global 的變數需加前綴 ``g:``

* Function argument 的參數需加前綴 ``a:``

* Local variable 需加前綴 ``l:``

* 使用 ``let a = 3`` 來賦值

語法
====

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
====

* 字串比對

  - 避免使用 ``==`` ，因為其 case sensitive 設定是根據 set ignorecase

  - case sensitive 的字串比對 ``==#``

  - case insensitive 的字串比對 ``==?``

  - 字串 match

    - case sensitive ``=~#``

    - case insensitive ``=~?``

    - pattern 建議用單引號字串

* 字串長度 (bytes) ::
  
    strlen(s)

* 字串 "外觀" 長度 ::
  
    strdisplaywidth(s)

* 重複字串 ::

    repeat('-', 80)

編輯區
======

* 取得編輯區內的內容
  
  - 取得游標所在的行的內容 ::
    
      getline('.')

  - 取得第 3 行的內容 ::
    
      getline(3)

* 游標位置
  
  - 取得游標所在的行數 ::
  
      line('.')

  - 取得游標的 column number ::

      col('.')

    - 1 indexed

  - 設定游標的位置 ::

      cursor({linenum}, {col})

* 設定游標所在行的內容 ::

    call setline('.', "abcd")

* 在游標所在行的下一行插入一行字串 ``xxx`` ::

    call append('.', "xxx")

內建函式
========

* 長度 ::
  
    len(list_a)

指令
====

* 執行 normal mode 下的動作 ::

    normal! <movements>
