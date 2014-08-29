private String get_wifi_mac_addr () {
    WifiManager wifiMan = (WifiManager) this.getSystemService(
        Context.WIFI_SERVICE);
    WifiInfo wifiInf = wifiMan.getConnectionInfo();
    return wifiInf.getMacAddress();
}

/* <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
 * is needed
 */
