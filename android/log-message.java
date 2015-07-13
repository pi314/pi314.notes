/* static or not depends on situation */
public void onCreate (bla) {
    /* Create session filter */
    Log.e("TAG", "create session filter");
}

static private Handler message_handler;
static private String handler_tag = "defema.log";

static public void set_handler (Handler message_handler, String handler_tag) {
    DeFeMa.message_handler = message_handler;
    DeFeMa.handler_tag = handler_tag;
}

static private boolean logging = true;
static private String log_tag = "DeFeMa";

static public void set_log_tag (String log_tag) {
    DeFeMa.log_tag = log_tag;
}

static private void notify_message (String message) {
    
    if ( !logging ) return;
    
    Log.i(log_tag, "[DeFeMa] " + message);

    // Log.v: ?
    // Log.d: blue
    // Log.i: green
    // Log.w: yellow
    // Log.e: red
    
    if (message_handler == null) return;
    
    Message msgObj = message_handler.obtainMessage();
    Bundle b = new Bundle();
    b.putString(handler_tag, message);
    msgObj.setData(b);
    message_handler.sendMessage(msgObj);
}
