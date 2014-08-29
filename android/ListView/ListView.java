lv.setOnItemClickListener(new OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent,
            View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, SendMessage.class);
        String message = "abc";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
});
