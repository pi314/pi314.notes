LinearLayout seriesMainListItemView = (LinearLayout) findViewById(R.id.SeriesMainListItemView);
LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

for (int i=0; i<scheduleArr.length; i++) {

    View inflatedView = mInflater.inflate(R.layout.scheduleitem, null);
    TextView inflatertext1 = (TextView) inflatedView.findViewById(R.id.text1);
    TextView inflatertext2 = (TextView) inflatedView.findViewById(R.id.text2);
    inflatertext1.setText(scheduleArr[i][0]);
    inflatertext2.setText(scheduleArr[i][1]);
    Log.i("data",i + " " + scheduleArr[i][0] + "/" + scheduleArr[i][1]);
    seriesMainListItemView.addView(inflatedView);
}
