public int parse_int (String input) {
    try {
        return Integer.parseInt(input);
    } catch (NumberFormatException e) {
        return 0;
    }
}

/* TextView */
public String set_textview_content (int textview_id, String content) {
    final TextView tv = (TextView) findViewById(textview_id);
    tv.setText(content);
}

/* EditText */
public String get_edittext_content (int edittext_id) {
    final EditText et = (EditText)findViewById(edittext_id);
    return et.getText().toString();
}

public String get_edittext_content (EditText et) {
    return et.getText().toString();
}

public void set_edittext_content (int edittext_id, String content) {
    final EditText et = (EditText)findViewById(edittext_id);
    et.setText(content);
}

public void set_edittext_content (EditText et, String content) {
    et.setText(content);
}

/* Buttons */
public void set_button_text (int button_id, String content) {
    final Button btn = (Button) findViewById(button_id);
    btn.setText(content);
}
