boolean file_readable (String file_path) {
    if ( !isExternalStorageReadable() )
        return false;

    File f = new File(db_path + db_name, "read");

    if ( !f.exists() ) {
        return false;
    }

    if ( !f.canRead() )
        return false;

    try {
        FileReader fileReader = new FileReader(f);
        fileReader.read();
        fileReader.close();
        return true;
    } catch (Exception e) {
        return false;
    }
}
