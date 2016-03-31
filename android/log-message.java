static private void logging (String message) {
    String padding = message.startsWith(" ") || message.startsWith("[") ? "" : " ";
    Log.i(log_tag, String.format("[%s][%s]%s%s%n", log_tag, local_log_tag, padding, message));

    // Log.v: ?
    // Log.d: blue
    // Log.i: green
    // Log.w: yellow
    // Log.e: red
}
