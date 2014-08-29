/* static or not, it depends on situation */
static private boolean logging = true;

static private void log_message (String message) {
    
    if ( !logging ) return;
    
    System.out.println("[DeFeMa] " + message);
    // you can also use Log.d(); or whatever
    
    // you can also notify_message();
}
