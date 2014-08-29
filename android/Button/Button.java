Button btn = (Button)findViewById(R.id.btn);
btn.setOnClickListener(new View.OnClickListener () {
    @Override
    public void onClick (View v) {

    }
});

// trigger click event
// only UI thread can do this
btn.performClick();
