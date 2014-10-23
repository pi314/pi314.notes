- 設定 chart view 的 Layout 大小

  - chart view 預設大小都是 match_parent，不設定大小的話會佔滿所有空間

  1.  設定 layout paramaters::
      
      graphActivityLayout.addView(mChart，1);
      LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mChart.getLayoutParams();
      layoutParams.

  2.  包一層 view，固定外面的 view 大小
      - worked

- 設定 chart view 的顯示範圍 ::

    XYMultipleSeriesRenderer mRenderer;
    mRenderer.setXAxisMin(minX);
    mRenderer.setXAxisMax(maxX);
    mChartView.repaint();

- 設定 chart 的背景色 ::

    XYMultipleSeriesRenderer mRenderer;
    mRenderer.setApplyBackgroundColor(true);
    mRenderer.setBackgroundColor(Color.RED);
    mRenderer.setMarginsColor(Color.RED);

- 設定座標軸的顏色 ::

    XYMultipleSeriesRenderer mRenderer;
    mRenderer.setYLabelsColor(0, Color.BLACK);  // 依 renderer 數量及順序而定

- 其他 ::

    renderer.setShowGrid(true);
