===================
= Vim script 筆記 =
===================

變數
====

- 變數沒有型態，且為弱型別（不同型態的資料運算時可能會自己轉型）

- Global 的變數需加前綴 ``g:``

- Function argument 的參數需加前綴 ``a:``

- Local variable 需加前綴 ``l:``

- 使用 ``let a = 3`` 來賦值

語法
====

- if condition ::

    if <condition> 
    elseif <condition>
    endif

- while loop ::

    while <condition>
    endwhile

- for loop ::

    for i in list
    endfor

字串
====

- 字串比對

  - 避免使用 ==，因為其 case sensitive 設定是根據 set ignorecase

  - case sensitive 的字串比對 ==#

  - case insensitive 的字串比對 ==?

  - 字串 match

    - case sensitive =~#

    - case insensitive =~?

內建函式
========

- 字串長度 ``strlen(s)``

- 長度 ``len(list_a)``
