package com.mybaby.android_final_project.commons;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.model.MeasurePerMonth;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

public class ProgressChartActivity extends Activity {

    private GraphicalView mChart;
    public static final int DATA_SIZE = 8;
    public static final int MONTHS = 24;
    public static final int FIRST_COLUMN = 0;
    private Double[] standardChart = null;
    private String gender = null;
    private List<MeasurePerMonth> babyMeasures;
    private String measureTableTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
    }

    protected void openChart() {
        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        // Create Default Series to the dataset
        for (int column = 1; column < DATA_SIZE; column++) {
            dataset.addSeries(getDataPointLineGraphSeries(column, getStandardChart()));
        }
        dataset.addSeries(getDataFromControls(babyMeasures));

        CustomMultiRenderer multiRenderer = new CustomMultiRenderer(this, measureTableTitle+" for Age", measureTableTitle);
        for (int column = 1; column < DATA_SIZE; column++) {
            XYSeriesRenderer defaultRenderer = makeXYSeriesRenderer(Color.WHITE, 2, multiRenderer.getVal());
            //add render
            multiRenderer.addSeriesRenderer(defaultRenderer);
        }

        // Creating XYSeriesRenderer to customize visitsSeries
        XYSeriesRenderer babyBoyRenderer = makeXYSeriesRenderer(Color.BLUE, 10, multiRenderer.getVal() * 1.5f);
        multiRenderer.addSeriesRenderer(babyBoyRenderer);

        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);

        // Creating a Time Chart
        mChart = (GraphicalView) ChartFactory.getLineChartView(getBaseContext(), dataset, multiRenderer);

        // Adding the Line Chart to the LinearLayout
        chartContainer.addView(mChart);
    }


    @NonNull
    private XYSeriesRenderer makeXYSeriesRenderer(int white, int lineWidth, float val) {
        // Creating XYSeriesRenderer to customize visitsSeries
        XYSeriesRenderer visitsRenderer = new XYSeriesRenderer();
        visitsRenderer.setColor(white);
        visitsRenderer.setPointStyle(PointStyle.CIRCLE);
        visitsRenderer.setFillPoints(true);
        visitsRenderer.setLineWidth(lineWidth);
        visitsRenderer.setDisplayChartValues(true);
        visitsRenderer.setChartValuesTextSize(val);
        return visitsRenderer;
    }

    private XYSeries getDataPointLineGraphSeries(int column, Double[] chart) {
        XYSeries viewsSeries = new XYSeries("");
        for (int li = 0; li < MONTHS; li++) {
            int value = li * DATA_SIZE;
            double x = chart[FIRST_COLUMN + value];
            double y = chart[column + value];
            viewsSeries.add(x, y);
        }

        return viewsSeries;
    }

    private XYSeries getDataFromControls(List<MeasurePerMonth> measurePerMonths) {
        XYSeries viewsSeries = new XYSeries("");
        for (MeasurePerMonth m : measurePerMonths) {
            viewsSeries.add(m.getMonth(), m.getMeasure());
        }
        return viewsSeries;
    }

    public void setMeasureType() {

    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double[] getStandardChart() {
        return standardChart;
    }

    public void setStandardChart(Double[] standardChart) {
        this.standardChart = standardChart;
    }

    public void setBabyMeasures(List<MeasurePerMonth> babyMeasures) {
        this.babyMeasures = babyMeasures;
    }

    public void setMeasureTableTitle(String measureTableTitle) {
        this.measureTableTitle = measureTableTitle;
    }
}
