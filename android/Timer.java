Handler handler = new Handler();

private Runnable runnable = new Runnable() {
    @Override
    public void run() {
        //...
        handler.postDelayed(this, 1000);
    }
};

handler.postDelayed(runnable, 100);

// handler.removeCallbacks(runnable);
