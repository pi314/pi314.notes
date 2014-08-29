Handler handler = new Handler() {
    public void handleMessage (Message msg) {
        int what = msg.getData().getInt("what");
        //String message = msg.getData().getString("message");
        if (what == 0) {
            show_alert_dialog("Connection message", tree_ip + " connected");
        }
    }
};

////////////////////

Message msgObj = handler.obtainMessage();
Bundle b = new Bundle();
b.putInt("what", 0);
//b.putString("message", "Connected");
msgObj.setData(b);
handler.sendMessage(msgObj);
