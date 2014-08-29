public void show_alert_dialog (String title, String message) {
    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
    dialog.setTitle(title);
    dialog.setMessage(message);

    final EditText input = new EditText(this);
    dialog.setView(input);
    
    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener () {
        public void onClick (DialogInterface dialog, int id) {
            String value = input.getText();
        }
    });

    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener () {
        public void onClick (DialogInterface dialog, int id) {
            dialog.cancel();
        }
    });

    dialog.create().show();
}
