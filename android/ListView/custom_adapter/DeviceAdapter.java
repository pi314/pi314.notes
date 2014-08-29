package com.example.shake2comport;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DeviceAdapter extends ArrayAdapter<BluetoothDevice> {
    
    Context context;
    int layout_resource_id;
    ArrayList<BluetoothDevice> data = null;
    
    public DeviceAdapter (Context context, int layout_resource_id, ArrayList<BluetoothDevice> data) {
        super(context, layout_resource_id, data);
        this.layout_resource_id = layout_resource_id;
        this.context = context;
        this.data = data;
    }
    
    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DeviceHolder  holder = null;
        
        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layout_resource_id, parent, false);
            
            holder = new DeviceHolder ();
            holder.device_name = (TextView)row.findViewById(R.id.device_name);
            holder.device_addr = (TextView)row.findViewById(R.id.device_addr);
            
            row.setTag(holder);
            
        } else {
            holder = (DeviceHolder)row.getTag();
        }
        
        BluetoothDevice device = data.get(position);
        
        String name_str = device.getName();
        if (name_str == null) {
            name_str = "(null)";
        }
        holder.device_name.setText(name_str);
        holder.device_addr.setText(device.getAddress());
        
        return row;
    }

    static class DeviceHolder {
        TextView device_name;
        TextView device_addr;
    }
}
