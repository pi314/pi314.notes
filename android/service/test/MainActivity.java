package com.example.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    
    private TestService my_bound_service;
    private boolean connected_to_service;
    
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            my_bound_service = ((TestService.MyBinder)service).getService();
            display("Service connected");
        }

        public void onServiceDisconnected(ComponentName className) {
            my_bound_service = null;
            display("Service disconnected");
        }
    };
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        my_bound_service = null;
        connected_to_service = false;
        
        connect_to_service();
        
        display_list();
        
        Button btn_stop_service = (Button)findViewById(R.id.btn_stop_service);
        btn_stop_service.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                stop_service();
            }
        });
        
        Button btn_refresh = (Button)findViewById(R.id.btn_refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                disconnect_to_service();
                connect_to_service();
                display_list();
            }
        });

    }
    
    public void connect_to_service () {
        Intent i = new Intent (this, TestService.class);
        bindService(
            i,
            mConnection,
            Context.BIND_AUTO_CREATE);
        startService(i);
        connected_to_service = true;
    }
    
    public void disconnect_to_service () {
        if (connected_to_service) {
            unbindService(mConnection);
            connected_to_service = false;
        }
    }
    
    public void stop_service () {
        stopService(new Intent(this, TestService.class));
        /* unbindService() after stopService() is needed
         * according to my test, if you don't unbindService after stopService(),
         * the service will keep running until this activity destroied
         */
        unbindService(mConnection);
    }
    
    public void display (String message) {
        TextView tv_display = (TextView) findViewById(R.id.tv_display);
        tv_display.append(message + "\n");
    }
    
    public void display_list () {
        if (connected_to_service) {
            
            if (my_bound_service == null) {
                display("service bound failed\n");
                return;
            }
            else if (my_bound_service.get_list() == null) {
                display("list null\n");
                return;
            }
            
            for (String i : my_bound_service.get_list() ) {
                display(i);
            }
            
        }
        
    }
    
    @Override
    protected void onDestroy () {
        super.onDestroy();
        disconnect_to_service();
    }
    
}

