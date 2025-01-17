===============================================================================
Polyglot
===============================================================================

一些 Polyglot 招式


註解
-----------------------------------------------------------------------------
C/C++:

.. code:: cpp

  // comment

  /* multi-line
  comment */

  #if 0
  Same effect as comments
  #endif

  #ifndef __cplusplus
  Ignored by C++
  #endif

  #ifdef __cplusplus
  Ignored by C
  #endif

--------

Python:

.. code:: python

  # comment

  '''
  multi-line string
  that sometimes could be used as comments
  '''

  """
  double quoted multi-line string
  """

  'string'; "string"

--------

Shell:

.. code:: shell

  # comment

--------

Vim:

.. code:: vim

  " comment


Python & Shell
-----------------------------------------------------------------------------
Shell script 不只參數，指令也可以可以加引號。用這個特性可以排除 Python:

.. code:: shell

  'echo' 'shell'

--------

``exit`` 在 Python 裡是 function，在 shell script 裡則是指令。這個差異可以用來排除 shell:

.. code:: python

  exit
  print('python')

執行到 ``exit`` 以後，shell 的部份就會結束，後面的 code 就算有語法錯誤也不會有影響


C++ & Python
-----------------------------------------------------------------------------
``#if 0`` / ``#endif`` 包含的 code 可以排除 C++:

.. code:: python

  #if 0
  print('python')
  #endif

--------

``#if 0`` / ``#endif`` 裡面放入 Python 的多行字串 ``'''`` ，可以再用來排除 Python

.. code:: cpp

  #if 0
  '''
  #endif
  std::cout << "C++" << std::endl;
  #if 0
  '''
  #endif

--------

``/*`` / ``*/`` 搭配 ``#`` ，也可以分別隔出 C/C++ 和 Python 的空間:

.. code:: python

   #include <stdio.h> /*
   print("Hello, Python!")
   # */ int main() { puts("Hello, C!"); }

--------

Macro 可以讓 C/C++ 稍微配合 Python 的語法，讓一些 code 同時相容 C/C++ 和 Python:

.. code:: cpp

  #if 0
  LANG = 'Python'
  #endif
  #define LANG "C/C++"
  #define print(str) printf(str "\n");

  print(LANG)

--------

另一個方式是利用行尾的反斜線 ``\\``:

.. code:: python

  #define NOTHING 0 // \
      more code

在這個例子裡，C/C++ 會將 ``more code`` 視為前一行的延續，所以會被註解掉

Python 則是會將它當成獨立的一行，產生 ``IndentationError: unexpected indent``


Python & Vim
-----------------------------------------------------------------------------
Vim 的雙引號 ``"`` 是註解，後面再接一個雙引號的話，在 Python 裡會形成字串

放一個分號 ``;`` 做結束以後，後面可以開始接 Python code

.. code:: vim

  ""; print('python')

Python code 裡再放 ``'''`` 的話，可以再反過來排除 Python:

.. code:: vim

  ""; text = '''
  echom 'vim'
  ""'''.rstrip('"').rstip('\n')

如上所示，進入到 Python 多行字串的區域以後，根據寫法，在離開前有機會不用再加分號
``;`` ，並且可以把內容接到變數裡使用

--------

和 shell 類似，Vim 的 ``exit`` 也是指令，可以用來排除 vim:

.. code:: vim

  exit
  print('python')

--------

搭配前面的註解方式，Vim 的 ``function!`` 可以用來隔出一段不會被 Vim 執行的區塊:

.. code:: vim

  ""; '''
  function! __NOVIM__ ()
  ""'''
  print('python')
  ""; '''
  endfunction
  ""'''
