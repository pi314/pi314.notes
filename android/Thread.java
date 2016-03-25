class FooThread implements Thread {
    private int value;

    public FooThread (int a) {
        value = a;
    }

    public FooThread () {
        value = 0;
    }

    @Override
    public void run () {
        /* do some stuff */
    }
}

Thread t = new FooThread();
t.start();
t.join();   // Blocks until ``t`` ends

// ``t`` cannot be reused after finished,
// if you need to re-run it, create a new object
