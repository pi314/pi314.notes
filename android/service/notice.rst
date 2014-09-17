If you are starting a service from an Activity, which is in a TabActivity,

then you have to use `getApplicationContext().startService(intent);` instead of just `startService(intent)`

OR the service will be killed without `onDestroy()` after the TabActivity being destroyed.
