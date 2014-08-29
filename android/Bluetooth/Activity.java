public class MainActivity extends Activity {

    /* this constant is a tag for requesting enable bluetooth */
    private final int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* initializing */
        BluetoothManager.init(bluetooth_handler);

        /* This method is recommanded way to enable bluetooth:
         *  Prompt for user choose
         */
        if ( !BluetoothManager.bluetooth_enabled() ) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(BluetoothManager.receiver, filter);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        
        /* onActivityResult() may be used by many codes, so we need a tag REQUEST_ENABLE_BT
         * to filter out other events
         */
        if( (requestCode == REQUEST_ENABLE_BT) && (resultCode == RESULT_OK) ){
            BluetoothManager.start_discovery();
            
            /* this must be after BluetoothManager.init*/
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(BluetoothManager.receiver, filter);
            
        }

    }

}
