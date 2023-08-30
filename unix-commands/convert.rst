===============================================================================
convert
===============================================================================
ImageMagick 為一套開放原始碼的影像處理軟體，其中一個 command line utility 為 ``convert``

* 從一張 png 之中切出一部份 ::

    $ convert -crop 80x80+60+40 input.png +repage output.png

  - Geomatry: 寬 x 高 + x-offset + y-offset
  - ``+repage`` 會把 offset 資訊從 meta data 裡移除
  - 對 gif 切割時，建議把 x-offset 和 y-offset 都加上（就算是 0），不然每個 frame 會被分成多個小塊，產生更多的 frame 並閃爍

* 把一張圖放大成兩倍 (長寬都兩倍) ::

    $ convert input.png -scale 200% output.png

* 將一張 png 以 80x80 的尺寸切割 ::

    $ convert -crop 80x80 input.png +repage output-%d.png

  - ``%d`` 會被代換成從 0 開始的編號

* 將一張 png 的透明部份取代為白色 ::

    $ convert input.png -background white -alpha remove output.png

* 將一些 png 合併成 gif 並設定 delay ::

    $ convert -delay 2x100 {1..4}.png out.gif

  - ``2x100`` 表示 ``2`` 倍的 ``1/100`` 秒
  - 設定得太快的話反而會被降速
