static public boolean file_ok (String path) {
    File f = new File(path);
    if ( f.exists() ) {
        return true;
    }

    try {

        f.createNewFile();
        return true;

    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

}

/* Checks if external storage is available for read and write */
static public boolean isExternalStorageWritable() {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
        return true;
    }
    return false;
}

/* Checks if external storage is available to at least read */
static public boolean isExternalStorageReadable() {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state) ||
        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
        return true;
    }
    return false;
}
