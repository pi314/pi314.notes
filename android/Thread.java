/*
    If you need one Thread wait for another Thread finish,
     use Handler.
*/

new Thread(new ClientThread()).start();

// new Thread(new ClientThread(314)).start();

////////////////////

/*static */class ClientThread implements Runnable {

    private int value;

    public ClientThread (int a) {
        value = a;
    }

    public ClientThread () {
        value = 0;
    }

    @Override
    public void run () {
        /* do some stuff */
        notify_message(CONST.JOB_DONE, ClientThread);
    }
}
