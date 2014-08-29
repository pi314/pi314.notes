private static BluetoothSocket create_bluetooth_socket(BluetoothDevice device)
    throws IOException, Exception {
    if(Build.VERSION.SDK_INT >= 10){
        final Method  m = device.getClass().getMethod(
            "createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class }
            );
        return (BluetoothSocket) m.invoke(device, MY_UUID);
    }
    return  device.createRfcommSocketToServiceRecord(MY_UUID);
}

public boolean connect (String device_addr) {
    BluetoothDevice target_device = BluetoothManager.bt_adapter.getRemoteDevice(device_addr);

    return connect(target_device);
}

public boolean connect (BluetoothDevice target_device) {
    try {
        bt_socket = create_bluetooth_socket(target_device);
        bt_socket.connect();
        out_stream = bt_socket.getOutputStream();
        return true;

    } catch (IOException e) {
        return false;
    } catch (Exception e) {
        return false;
    }
}

public void disconnect () {
    try {

        out_stream.close();
        out_stream = null;

        bt_socket.close();
        bt_socket = null;

    } catch (IOException e) {

    }
}

public void send_data (String data) {
    try {
        out_stream.write(data.getBytes());
    } catch (IOException e) {
    }
}

