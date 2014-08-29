/* HandlerThread is a thread with handler
 * you can use this special thread to handle message
 * and send something to Internet
 **/

handler_thread = new HandlerThread("MyHandlerThread");
handler_thread.start();

Looper looper = handler_thread.getLooper();

data_handler = new Handler(looper, new Callback () {
    @Override
    public boolean handleMessage(Message msg) {
        // do something
        
        return true;
    }
});

data_handler.sendMessage();


