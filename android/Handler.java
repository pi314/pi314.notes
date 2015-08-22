import android.os.Handler;

/* Note: this method will not gernerate a new thread */
Handler handler = new Handler() {
    public void handleMessage (Message msg) {
        //int what = msg.getData().getInt("what");
        String message_type = msg.getData().getString("type");
        if ( message_type.equals("client-socket") ) {
            String message = msg.getData().getString("message");
            //...
        }
    }
};

public void notify_message (String type, String message) {
    Message msgObj = handler.obtainMessage();
    Bundle b = new Bundle();
    b.putString("type", type);
    b.putString("message", message);
    msgObj.setData(b);
    handler.sendMessage(msgObj);
}

////////

Handler handler = new Handler();

private Runnable runnable = new Runnable() {
    @Override
    public void run() {
        //...
        handler.postDelayed(this, 1000);
    }
};

handler.postDelayed(runnable, 100);

// handler.removeCallbacks(runnable);
