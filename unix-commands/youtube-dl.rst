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

    $ youtube-dl URL

* 查詢特定 youtube 影片有哪些可用的格式 ::

    $ youtube-dl URL -F

* 指定下載的格式 ::

    $ youtube-dl URL -f <format code>

* 指定高品質音訊 / 指定高品質視訊並合併 ::

    $ youtube-dl URL -f bestvideo+bestaudio
