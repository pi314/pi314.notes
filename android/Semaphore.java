import java.util.concurrent.Semaphore;

Semaphore readlock = new Semaphore(10);
readlock.acquire();
readlock.release();
