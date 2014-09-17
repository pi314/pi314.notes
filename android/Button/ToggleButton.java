final ToggleButton btn = (ToggleButton) findViewById(R.id.btn);
btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // The toggle is now enabled
        } else {
            // The toggle is now disabled
        }
    }
});

btn.setChecked(true);
btn.setTextOff("off");
btn.setTextOn("on");

public void set_toggle_button_state (int toggle_button_id, boolean state) {
    final ToggleButton tb = (ToggleButton) findViewById(toggle_button_id);
    tb.setChecked(state);
}
