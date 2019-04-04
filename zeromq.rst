===============================================================================
ZeroMQ
===============================================================================
ZeroMQ 把 socket 包裝起來並重新定義它們，每種 ZeroMQ socket 有不同的行為和使用方式。

經過這層包裝， ``bind()`` / ``connect()`` / ``send()`` 都可能不會馬上生效。

使用者透過 ZeroMQ socket 把架構裡的各個 node 串起來。

在架構裡，固定的部份（通常是 server）使用 ``bind()`` ，不固定的部份（通常是可以隨時增減的 client）使用 ``connect()`` 。


Request-Reply Pattern (REQ-REP)
-------------------------------------------------------------------------------
* 一來、一回
* (server) REP 要在 ``recv()`` 以後才可以 ``send()``
* (client) REQ 要在 ``send()`` 以後才可以 ``recv()``
* Socket 自己透過 state 維護並限制這個順序，不照著做的話就會 ``zmq.error.ZMQError: Operation cannot be accomplished in current state``
* 所以 server 在 ``send()`` 的時候不需要指定 client（因為被限制只能回給剛收到的 client）


Publish-Subscribe Pattern (PUB-SUB)
-------------------------------------------------------------------------------
* 單向
* (server) PUB 可以隨時 ``send()``
* (client) SUB 可以隨時 ``recv()``

  - 晚來的 client 會漏掉之前 PUB 出去的訊息

* Client 一定要設定 filter： ``socket.setsockopt_string(zmq.SUBSCRIBE, prefix)`` ，不然會什麼也收不到


Push-Pull Pattern (PUSH-PULL)
-------------------------------------------------------------------------------
* 單向
* 像 PUB-SUB，但 PUSH 會平均的把資料分到 PULL 端


PAIR-PAIR
-------------------------------------------------------------------------------
* 雙向？
* 只能接受一組成對的 PAIR 互連（所以叫做 pair）
* 沒有限制傳訊息的順序，目前看起來最像普通的 TCP socket
* PAIR socket 主要是設計給 thread 之間溝通用的（ ``inproc://`` ），所以不會自動重新 ``connect()``


多對多 Pattern (XPUB-XSUB) (ROUTER-DEALER)
-------------------------------------------------------------------------------
* 當 server 增加時，client 們全都需要修改，因為需要 ``connect()`` 到新的 server
* 當你有一大堆 client 時這樣就是個問題
* 解法

  - 放一隻 broker 在中間
  - 對於 REQ-REP

    + 把 socket 接成 [REQ(s)] - [ROUTER|DEALER] - [REP(s)]
    + 這隻 broker 需要用 ``poll()`` 做 non-blocking 的 relay
    + ZeroMQ 有為這種簡單的場景做包裝： ``zmq.proxy(frontend, backend)``

  - 對於 PUB-SUB

    + 把 socket 接成 [PUB(s)] - [XSUB|XPUB] - [SUB(s)]


REQ-REP 和 ROUTER-DEALER 的其他細節
-------------------------------------------------------------------------------
ZeroMQ 在邏輯上是以一個 frame 為單位在傳訊息的，每個 frame 之間不會混在一起。

* REQ 的邏輯

  A)  ``send(data)``
  B)  在 data 前補上一個 null frame（變成 ``[, data]`` ）
  C)  送出
  D)  收到訊息，預期第一個是 null frame；把它拿掉，下一個 frame 透過 ``recv()`` 回傳

* REP 的邏輯

  A)  收到一串 frame，把 source address 記下來
  B)  從開頭從下找直到 null frame 為止，記錄起來；把 null frame 的下一個 frame 透過 ``recv()`` 回傳
  C)  ``send(data)``
  D)  把剛才記錄的 frame 們安插回 data 的前面，送回給 source

* ROUTER 的邏輯

  A)  收到一串 frame，把 source address 記下來
  B)  隨機產生一個 ID，記下來，同時加在這串 frame 前面
  C)  透過 ``recv_multipart()`` 把整串 frame 回傳

* DEALER 的邏輯

  - ``send_multipart(frames)``
  - 整串全部送出去，不用記東西

考慮 Client[REQs] - Broker[ROUTER-DEALER] - Server[REP] 的架構，送出 ``Hello`` 、回傳 ``World`` 的流程：

1.  Client: ``send("Hello")``
2.  REQ 送出 ``[, "Hello"]`` 給 ROUTER
3.  ROUTER 記下 ``(client address, id)`` ， ``recv()`` 得到 ``[id, , "Hello"]``
4.  DEALER 送出 ``[id, , "Hello"]``
5.  REP 記下 ``(broker address, [id, ])`` ， ``recv()`` 得到 ``"Hello"``
6.  Server: ``send("World")``
7.  REP 送出 ``[id, , "World"]`` 給 broker
8.  DEALER 收到 ``[id, , "World"]``
9.  ROUTER 透過 id 查詢得到 client address，送出 ``[, "World"]``
10. REQ 收到 ``[, "World"]`` ， ``recv()`` 得到 ``"World"``


所有合理的 Socket 組合
-------------------------------------------------------------------------------
* PUB / SUB
* REQ / REP
* REQ / ROUTER (take care, REQ inserts an extra null frame)
* DEALER / REP (take care, REP assumes a null frame)
* DEALER / ROUTER
* DEALER / DEALER
* ROUTER / ROUTER
* PUSH / PULL
* PAIR / PAIR
