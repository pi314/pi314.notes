<SeekBar
    android:id="@+id/bar_bar"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:layout_margin="10dp"
    android:progress="20"
    android:secondaryProgress="20" />


/* ****************************** */
SeekBar bar_bar = null;

bar_bar = (SeekBar) findViewById(R.id.bar_bar);
bar_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    TextView tv = (TextView)findViewById(R.id.tv_sensitive);

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        sensitive = progress;
        if (sensitive == 0) {
            bar_bar.setProgress(1);
            sensitive = 1;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
});
