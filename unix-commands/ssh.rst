=======
``ssh``
=======
* ``ssh -t <jump_host> ssh <target_host>``

  - 透過 ``<jump_host>`` 做為跳板，ssh 至 ``<target_host>``

* ``ssh -D <port> <user>@<host>``

  - 在 Local 端開 ``<port>`` 做為 Proxy，並把流量透過 ssh 導往 ``<host>`` 轉發
