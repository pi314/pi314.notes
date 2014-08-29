/**** FirstActivity ****/
Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
Bundle b = new Bundle();
b.putInt("key", 1); //Your id
intent.putExtras(b); //Put your id to your next Intent
startActivity(intent);


/**** SecondActivity ****/
public void onCreate (...) {
    Bundle b = getIntent().getExtras();
    int value = b.getInt("key");
}
