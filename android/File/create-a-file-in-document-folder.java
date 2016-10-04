File document_folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
File my_file = new File(document_folder, "filename.txt");
PrintStream my_file_writer;

if (!document_folder.mkdirs()) {
    // error
}

try {
    my_file_writer = new PrintStream(new FileOutputStream(my_file), true);
} catch (IOException e) {
    e.printStackTrace();
}

if (my_file_writer == null) {
    // error
}

my_file_writer.println("pen");
my_file_writer.println("pineapple");
my_file_writer.println("apple");
my_file_writer.println("pen");

my_file_writer.close();
