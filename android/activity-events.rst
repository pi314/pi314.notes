==========================
Events in Android Activity
==========================

* ``public void onCreate (Bundle savedInstanceState);``

  - Activity 做一般初始化，如產生 views，把資料 binding 到 List 上等等
  - 在這個函式呼叫 ``finish()`` 會導致 ``onDestroy()`` 立刻被呼叫
  - Next: ``onStart()``

* ``public void onRestart ();``

  - 在 Activity ``onStop()`` 後又再度被放回前景時觸發的事件
  - Next: ``onStart()``

* ``public void onStart ();``

  - Activity 開始可見
  - Next: ``onResume()``

* ``public void onResume ();``

  - Activity 開始和 User 互動
  - Next: ``onPause()``

* ``public void onPause ();``

  - Activity 被放到背景
  - 可用 ``isFinishing()`` 檢查 Activity 是否確定要關閉
  - Next: ``onStop()``

* ``public void onStop ()``

  - Activity 不再可見
  - **可能不會被呼叫**
  - Next: ``onDestroy()``

* ``public void onDestroy ()``

  - Activity 被清除前觸發的事件
  - **可能不會被呼叫**

* ``public void onBackPressed ()``

  - 使用者按下 Back 鍵時觸發的事件

* ``public boolean onKeyDown (int keyCode, KeyEvent event)``

  - ``return false`` 代表不處理

* ``public boolean onKeyLongPress (int keyCode, KeyEvent event)``

  - ``return false`` 代表不處理
