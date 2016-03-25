import java.util.concurrent.Semaphore;

Semaphore readlock = new Semaphore(10);
try {
    readlock.acquire();
    readlock.release();
} catch (InterruptedException) {
} finally {
    // no need to release here
    // because InterruptedException means readlock did not get any permission
}
