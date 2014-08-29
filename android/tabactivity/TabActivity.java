public class MainActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabSpec tab2 = tabHost.newTabSpec("Second Tab");

        tab1.setIndicator("TCP Client");
        tab1.setContent(new Intent(this,TCPClientTab.class));

        tab2.setIndicator("TCP Server");
        tab2.setContent(new Intent(this,TCPServerFragment.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
     }
}
