===============================================================================
convert
===============================================================================
ImageMagick 為一套開放原始碼的影像處理軟體，其中一個 command line utility 為 ``convert``

* 從一張 png 之中切出一部份 ::

    $ convert -crop 80x80+60+40 input.png +repage output.png

  - Geomatry: 寬 x 高 + x-offset + y-offset
  - ``+repage`` 會把 offset 資訊從 meta data 裡移除

* 將一張 png 以 80x80 的尺寸切割 ::

    $ convert -crop 80x80 input.png +repage output-%d.png

  - ``%d`` 會被代換成從 0 開始的編號

* 將一張 png 的透明部份取代為白色 ::

    $ convert input.png -background white -alpha remove output.png

* 將一些 png 合併成 gif ::

    $ convert 1.png 2.png 3.png 4.png out.gif
