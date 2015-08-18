devices = new ArrayList<BluetoothDevice>();

// DeviceAdapter is defined in DeviceAapter.java
adapter = new DeviceAdapter(context, R.layout.device_list_item, devices);

lv.setAdapter(adapter);

// ...

devices.add(device);
adapter.notifyDataSetChanged();

// ...
devices.clear();
adapter.notifyDataSetChanged();
