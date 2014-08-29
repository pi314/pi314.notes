BluetoothAdapter bt_adapter = null;
BluetoothSocket bt_socket = null;
OutputStream out_stream = null;

public void init () {

    bt_adapter = BluetoothAdapter.getDefaultAdapter();

    receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                /* found a new device */

            }
        }
    };

}

