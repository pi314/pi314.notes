public void setup_defocus_event (View view) {
    //if ( !(view instanceof Button) && view instanceof TextView ) {
    if ( !(view instanceof EditText) ) {
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(MainActivity.this);
                return false;
                
            }
        });
    }
    
    if (view instanceof ViewGroup) {

        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

            View innerView = ((ViewGroup) view).getChildAt(i);

            setup_defocus_event(innerView);
        }
    }
    
}
