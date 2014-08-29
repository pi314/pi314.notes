static int screen_width;
static int screen_height;

protected void onCreate(Bundle savedInstanceState) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    screen_width = displayMetrics.widthPixels;
    screen_height = displayMetrics.heightPixels;
}

@Override
public boolean onTouchEvent(MotionEvent event) {
    if (control_source != "TOUCH") {
        // don't keep this event
        return false;
    }
    int px = (int)event.getX();
    int py = (int)event.getY();
    float x = (float)px/(float)screen_width;
    float y = (float)py/(float)screen_height;
    switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            // Do nothing
            break;
            
        case MotionEvent.ACTION_MOVE:
            send_data("move", x, y);
            break;
            
        case MotionEvent.ACTION_UP:
            // Do nothing
            break;
    }
    // don't keep this event
    return false;
}
