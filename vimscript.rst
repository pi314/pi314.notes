===================
= Vim script 筆記 =
===================

語法
====

if <condition> 
elseif <condition>
endif

-   字串比對
    避免使用 ==，因為其 case sensitive 設定是根據 set ignorecase
    case sensitive 的字串比對 ==#
    case insensitive 的字串比對 ==?

