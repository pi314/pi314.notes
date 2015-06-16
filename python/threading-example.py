import queue
import threading
import random
import time

# called by each thread
def f (arg):
    w = random.randint(0, 5)
    time.sleep(w)
    q.put( (arg, w) )

q = queue.Queue()

for i in range(10):
    t = threading.Thread(target=f, args=(i,))
    t.daemon = True     # this ensures thread ends when main process ends
    t.start()

for i in range(10):
    s = q.get()
    print(s)

