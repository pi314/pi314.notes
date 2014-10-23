package com.example.achartengine_test;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

public class LineGraph {

    private GraphicalView view;
    
    private TimeSeries dataset = new TimeSeries("Random Data"); 
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    
    private XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
    
    public LineGraph()
    {
        // Add single dataset to multiple dataset
        mDataset.addSeries(dataset);
        
        // Customization time for line 1!
        renderer.setColor(Color.BLACK);
        renderer.setPointStyle(PointStyle.SQUARE);
        renderer.setFillPoints(true);
        
        // Enable Zoom
        //mRenderer.setZoomButtonsVisible(true);
        
        mRenderer.setXTitle("Time step");
        
        // colors
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.WHITE);
        mRenderer.setMarginsColor(Color.LTGRAY);
        mRenderer.setAxesColor(Color.BLACK);
        
        mRenderer.setXLabelsColor(Color.BLACK);
        mRenderer.setYLabelsColor(0, Color.BLACK);
        mRenderer.setYTitle("Value"); // can't find axis title text color
        
        // text sizes
        mRenderer.setAxisTitleTextSize(20);
        mRenderer.setLabelsTextSize(20);
        
        // Add single renderer to multiple renderer
        mRenderer.addSeriesRenderer(renderer);
    }
    
    public GraphicalView getView(Context context) 
    {
        view =  ChartFactory.getLineChartView(context, mDataset, mRenderer);
        return view;
    }
    
    public void addNewPoints(Point p)
    {
        System.out.println(p.getX() +","+ p.getY());
        dataset.add(p.getX(), p.getY());
        if (p.getX() >= 5) {
            mRenderer.setXAxisMin(p.getX() - 5);
            mRenderer.setXAxisMax(p.getX() + 5);
            
            System.out.println("set range: ["+ (p.getX()-5) +","+ (p.getX()+5) +"]");
        }
    }
    
}
