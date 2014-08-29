BluetoothAdapter bt_adapter = null;
BluetoothSocket bt_socket = null;
OutputStream out_stream = null;

ArrayList<BluetoothDevice> devices;

public boolean check_bluetooth_state () {
    if(bt_adapter == null) {
        return false;
    }

    if (bt_adapter.isEnabled()) {
        return true;
    }
    return false;
}

public static void start_searching () {
    bt_adapter.startDiscovery();
    searching = true;

    devices.clear();
    adapter.notifyDataSetChanged();
}

public static void stop_searching () {
    notify_message("BLUETOOTH-STATUS", "Searching canceled");
    bt_adapter.cancelDiscovery();
    searching = false;
}

