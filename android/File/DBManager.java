package com.example.openmtc2bulb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Environment;

public class DBManager {
    static private final String sd_card = Environment.getExternalStorageDirectory().getAbsolutePath();
    static private final String db_dir  = sd_card + "/EasyConnect/";
    static private final String db_name = "history.json";
    static private final String db_path = db_dir + db_name;

    /**
     * Dump the database file into two JSONObject.
     * if the database file did not exist, create one with default item.
     * */
    static public JSONObject dump_db ()
            throws IOException, JSONException {

        if ( !db_exists() ) {
            create_empty_db();
        }

        if ( !db_readable() ) {
            return null;
        }

        File db_file = new File(db_path);

        BufferedReader br = null;
        try {
            br = new BufferedReader( new FileReader(db_file) );
            String tmp;
            String db_raw_data = "";
            while ((tmp = br.readLine()) != null) {
                // operation on data_line
                db_raw_data += tmp;
            }

            return new JSONObject(db_raw_data);

        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                throw e;
            }
        }

    }

    static public void write_db (JSONObject database)
            throws IOException {
        if ( !db_readable() ) {
            return;
        }

        if ( !db_exists() ) {
            return;
        }

        File db_file = new File(db_path);
        FileWriter fw = new FileWriter(db_file);
        fw.write(database.toString());
        fw.close();
    }

    static public boolean db_exists () {
        if ( !isExternalStorageReadable() )
            return false;

        return ( new File(db_path) ).exists();
    }

    static public boolean db_readable () {
        if ( !db_exists() )
            return false;

        File f = new File(db_path);

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

    static public boolean db_writable () {
        if ( !db_exists() )
            return false;

        File f = new File(db_path);

        if ( !f.canWrite() )
            return false;

        return true;
    }

    static public void create_empty_db ()
        throws IOException {

        String content = "{}";

        File db = new File(db_path);
        FileWriter fw = new FileWriter(db.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();

    }

    static public boolean db_ok () {

        if ( !isExternalStorageReadable() )
            return false;

        if ( !isExternalStorageWritable() )
            return false;

        File f = new File(db_path + db_name);
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

}
