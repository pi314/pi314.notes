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
-----------------------------------------------------------------------------
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

    + ``-to`` 可以直接給結束時間，不用自己算長度

* 把影片倒帶 ::

    $ ffmpeg -i 海龍王彼德.mp4 -vf reverse -af areverse 鱷魚龍尾牙.mp4

  - ``-vf reverse``: 使用 video filter "reverse"
  - ``-af areverse``: 使用 audio filter "areverse"


範例 - 把部份影片快轉
-----------------------------------------------------------------------------

* ``setpts`` 可以控制影像播放的速度
* ``atempo`` 可以控制聲音播放的速度 (最快只能２倍速，但可以重覆多次讓效果相乘)
* ``-filter_complex`` 可以一次套用多個 filter

  - 我沒能成功同時讓 ``-c:v`` 和 ``-c:a`` 生效

底下的範例把 ``input.mp4`` 剪成三段，中間那段加為８倍速，最後再接起來

::

  # sh
  orig_video=input.mp4
  fc8x="[0:v]setpts=PTS/8[v];[0:a]atempo=2.0,atempo=2.0,atempo=2.0[a]"

  clip () {
      ffmpeg -ss $1 -to $2 -i "${orig_video}" -c copy $3.mkv
  }

  ff () {
      ffmpeg -ss $1 -to $2 -i "${orig_video}" -filter_complex "$fc64x" -map "[v]" -map "[a]" $3.mkv
      ffmpeg -i $3.mkv -c:v copy -c:a aac $3_aac.mkv
      rm $3.mkv
  }

  clip 00:00:00 00:01:00 01_clip
  ff   00:01:00 00:02:00 02_ff
  clip 00:02:00 00:03:00 03_clip

  ls -1 *.mkv | awk '{print "file " $0 }' > clips.txt
  ffmpeg -f concat -safe 0 -i clips.txt -c copy output.mp4
