@Override
public void onBackPressed() {
    if (page == 1) {
        final LinearLayout ll_monitor_page1 = (LinearLayout) findViewById(R.id.ll_monitor_page1);
        ll_monitor_page1.setVisibility(View.VISIBLE);
        page = 0;
    } else {
        finish();
    }
}
