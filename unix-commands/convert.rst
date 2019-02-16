===============================================================================
convert
===============================================================================
ImageMagick 為一套開放原始碼的影像處理軟體，其中一個 command line utility 為 ``convert``

* 將一張 png 以 80x80 的尺寸切割 ::

    $ convert -crop 80x80 input.png output-%d.png

  - ``%d`` 會被代換成從 0 開始的編號

* 將一張 png 的透明部份取代為白色 ::

    $ convert input.png -background white -alpha remove output.png