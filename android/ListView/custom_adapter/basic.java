ArrayList<String> values = new ArrayList<String>();

values.add("string1");
values.add("STRING2");
values.add("sTrInG3");
values.add("StRiNg4");

// "this" refers to activity context
ArrayAdapter<String> adapter = new ArrayAdapter(
    this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

lv.setAdapter(adapter);

String[] values2 = values.toArray();
ArrayAdapter adapter = new ArrayAdapter(
    this, android.R.layout.simple_list_item_1, android.R.id.text1, values2);

values.add("XD");

// dynamically change content
adapter.notifyDataSetChanged();
