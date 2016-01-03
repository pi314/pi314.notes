import android.os.Handler;

class Struct {
    int i;
    String s;
}

/* Note: this method will not gernerate a new thread */
Handler handler = new Handler() {
    public void handleMessage (Message msg) {
        // Method 1: use Message.obj
        Struct st = msg.obj;

        // Method 2: use Bundle
        String message_type = msg.getData().getString("type");

        //...
    }
};

public void notify_message (String type, String message) {
    Message msgObj = handler.obtainMessage();
    // Method 1: use Message.obj
    msgObj.obj = new Struct();

    // Method 2: use Bundle
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
