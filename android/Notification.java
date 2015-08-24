// To avoid application being opened twice, add
// android:launchMode="singleTask"
// to the <activity> element
public void show_ec_status (boolean status) {
    show_ec_status_on_monitor_page(status);
    String text = status ? EasyConnect.EC_HOST : "connecting";
    NotificationManager notification_manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    NotificationCompat.Builder notification_builder =
        new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentTitle(C.dm_name)
        .setContentText(text);

    PendingIntent pending_intent = PendingIntent.getActivity(
        this,
        0,
        new Intent(this, MainActivity.class),
        PendingIntent.FLAG_UPDATE_CURRENT
    );

    notification_builder.setContentIntent(pending_intent);
    notification_manager.notify(NOTIFICATION_ID, notification_builder.build());
}
