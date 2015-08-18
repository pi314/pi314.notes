public void read_content_into_value_table () {
    BufferedReader br = null;
    try {
        br = new BufferedReader( new FileReader(file_path) );
        String data_line;
        while ((data_line = br.readLine()) != null) {
            // operation on data_line
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (br != null) br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
