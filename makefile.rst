=============
Makefile 筆記
=============

Makefile 可以處理 Task 之間的 Dependency，讓編輯過程自動化

基本語法
--------

::

    RULE1 = gcc
    RULE2 = value1 value2 value3

    target: dependency
    >---command1
    >---command2 && command3
    >---command4 ;\
    >--->---command5

  - ``>---`` 為一個 ``TAB`` 字元，不能使用空格
  - 在指令前加上 ``@`` 可以避免 make 把指令也輸出到畫面上

``make target`` 前會自動把所有 dependency 都 ``make`` 完成

變數
----

  - Assign

    + ``=``

      * 把舊的值直接覆蓋

    + ``A += B``

      * 把 ``B`` 接在 ``A`` 上

    + ``A ?= B``

      * 若 ``A`` 未曾定義過，給予 ``B`` 做為變數值，否則不動作

    + ``A != B``

      * 把 ``B`` 做為指令執行， ``stdout`` 做為 ``A`` 的值

  - 變數使用 ``${variable}`` 或 ``$(variable)`` 存取

    + 若用在 target，則 ``$(variable)`` 中的每個值 (以空白分隔) 都被視為 target
    + Dependency 亦同

  - ``$@`` 代表目前的 target
