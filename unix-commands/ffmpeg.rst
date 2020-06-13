===============================================================================
ffmpeg
===============================================================================
ffmpeg 超強

大致用法

::

  $ ffmpeg -i <input> -vf <filter> <output>
  $ ffmpeg -i <input> -filter_complex <filter> <output>

* ``-i <input>`` 可重覆多次
* ``-vf <filter>`` 代表「簡單」的 filter：一個輸入、一個輸出
* ``-filter_comlplext <filter>`` 代表「複雜」的 filter：多個輸入、多個輸出

範例
-------------------------------------------------------------------------------
* 從 mp4 影片產生 "palette" ::

    $ ffmpeg -i input.mp4 -vf palettegen palette.png

  - Palette 可以在之後轉檔時做為參考，讓畫質好一點
  - 從一堆 png 產生 "palette" ::

      $ ffmpeg -i input%2d.png -vf palettegen palette.png

* 從 mp4 影片產生 gif ::

    $ ffmpeg -i input.mp4 -i palette.png -filter_complex paletteuse output.gif

* 從 mp4 影片抽取 mp3 ::

    $ ffmpeg -i input.mp4 -f mp3 -ab 192000 -vn -ss 00:00:01 -t 00:05:01 output.mp3

  - ``-f mp3``: 表示輸出格式為 mp3
  - ``-ab 192000``: 192Kbps
  - ``-vn``: 不要影片
  - ``-ss 00:00:00``: 開始時間
  - ``-t 00:05:01``: 持續時間
