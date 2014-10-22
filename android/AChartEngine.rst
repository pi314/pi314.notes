- 設定 chart view 的 Layout 大小

  - chart view 預設大小都是 match_parent，不設定大小的話會佔滿所有空間

  1.  設定 layout paramaters::
      
      graphActivityLayout.addView(mChart，1);
      LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mChart.getLayoutParams();
      layoutParams.

  2.  包一層 view，固定外面的 view 大小

- 設定 chart view 的顯示範圍 ::

    XYMultipleSeriesRenderer.setRange()
    setYAxisMax(double max) // 設定 y 座標軸的範圍
    setYAxisMin(double min) // 設定 y 座標軸的範圍

    setXLabels(int xLabels) // 設定 x 座標點的數量
    XYMultipleSeriesRenderer.mRenderer.setXAxisMin(minX);
    XYMultipleSeriesRenderer.mRenderer.setXAxisMax(maxX);
    mChartView.repaint();

- 設定 chart 的背景色 ::

    mRenderer.setApplyBackgroundColor(true);
    mRenderer.setBackgroundColor(Color.RED);
    mRenderer.setMarginsColor(Color.RED);
