LinkedBlockingQueue<int> queue;

queue.put(3);

do {
    // this line blocks when queue is empty
    int data = queue.take();

    // process data
} while (!queue.isEmpty());
