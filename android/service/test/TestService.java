package com.example.servicetest;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class TestService extends Service {

    private final IBinder mBinder = new MyBinder();
    private ArrayList<String> list;
    
    public TestService () {
        this.list = new ArrayList<String>();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String new_data_string = "data" + System.currentTimeMillis();
        list.add(new_data_string);
        
        return Service.START_NOT_STICKY;
    }
    
    public ArrayList<String> get_list () {
        return list;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        TestService getService() {
            return TestService.this;
        }
    }

    @Override
    public void onDestroy () {
    }
}

