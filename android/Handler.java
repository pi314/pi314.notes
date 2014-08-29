static Handler handler;

/* Note: this method will not gernerate a new thread */
handler = new Handler() {
    public void handleMessage (Message msg) {
        //int what = msg.getData().getInt("what");
        String message_type = msg.getData().getString("type");
        if ( message_type.equals("client-socket") ) {
            String message = msg.getData().getString("message");
            //...
        }
    }
};

////////////

public void notify_message (String type, String message) {
    Message msgObj = handler.obtainMessage();
    Bundle b = new Bundle();
    b.putString("type", type);
    b.putString("message", message);
    msgObj.setData(b);
    handler.sendMessage(msgObj);
}
