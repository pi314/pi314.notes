===============================================================================
`youtube-dl <https://github.com/ytdl-org/youtube-dl>`_
===============================================================================

2021/10/25 現況

https://www.reddit.com/r/youtubedl/comments/qa3b1a/state_of_youtubedl_repositories/

youtube-dl 的作者不喜歡給別人碰自己的 code

→ youtube-dlc 出現 (C=community)
→ youtube-dlc 很快的運作不順
→ yt-dlp 出現 (p=plus)

https://github.com/yt-dlp/yt-dlp

-------------------------------------------------------------------------------

* 下載 youtube 影片 ::

    $ yt-dlp {URL}

* 查詢特定 youtube 影片有哪些可用的格式 ::

    $ yt-dlp {URL} -F

* 指定下載的格式 ::

    $ yt-dlp {URL} -f {format code}

* 指定高品質音訊 / 指定高品質視訊並合併 ::

    $ yt-dlp {URL} -f bestvideo+bestaudio

* 只下載指定的 index ::

    $ yt-dlp {URL} --playlist-items 1,2,4-5

* 在下載之間 sleep 幾秒 ::

    $ yt-dlp {URL} --sleep-interval 3
