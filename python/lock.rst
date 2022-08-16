===============================================================================
Lock / RLock / Condition / Semaphore / Event
===============================================================================

Lock
-----------------------------------------------------------------------------
就是 lock。

適合在每條 thread 地位對等的時候使用。

..  code::python

    lock = threading.Lock()
    lock.acquire()
    lock.release()
    lock.locked()


RLock
-----------------------------------------------------------------------------
Reentrant lock，同一個 thread 可以重覆 acquire()，
當然每個 acquire() 都要有對應的 release()。

適合在遞迴的時候使用。

..  code::python

    rlock = threading.RLock()
    rlock.acquire()
    rlock.release()


Condition
-----------------------------------------------------------------------------
Lock 的包裝。

有點複雜。

適合在有多個條件需要依賴同一個實體 lock 的時候。


Semaphore
-----------------------------------------------------------------------------
能夠容納多個 acquire() 的 "lock"。

Lock 相當於 value=1 的 semaphore。

..  code::python

    semaphore = threading.Semaphore(value=1)
    semaphore.acquire()
    semaphore.release()

``semaphore.release()`` 可以把 value 加到比原本還多，
``threading.BoundedSemaphore()`` 對這點做了限制。


Event
-----------------------------------------------------------------------------
最簡單的同步機制

適合用在 thread 有指揮和聽令分工的時候。

..  code::python

    event = threading.Event()
    event.is_set()
    event.set()
    event.clear()
    event.wait()


Barrier
-----------------------------------------------------------------------------
wait() 的 thread 達到指定數量以後會全部一起 release。
