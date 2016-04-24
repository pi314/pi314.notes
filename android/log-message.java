static public void logging (String local_tag, String format, Object... args) {
    logging(local_tag, String.format(format, args));
}

static public void logging (String local_tag, String message) {
    Log.i(Constants.log_tag, String.format("[%s][%s] %s%n", Constants.log_tag, local_tag, message));
}
