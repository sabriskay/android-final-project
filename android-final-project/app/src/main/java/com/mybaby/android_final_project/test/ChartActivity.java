package com.mybaby.android_final_project.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import com.mybaby.android_final_project.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ChartActivity extends Activity {

    private GraphicalView mChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        openChart();
    }

    private void openChart(){

        int count = 5;
        Date[] dt = new Date[5];
        for(int i=0;i<count;i++){
            GregorianCalendar gc = new GregorianCalendar(2012, 10, i+1);
            dt[i] = gc.getTime();
        }

        int[] visits = { 2000,2500,2700,2100,2800,2900};
        int[] views = {2200, 2700, 2900, 2800, 3200};

        // Creating TimeSeries for Visits
        TimeSeries visitsSeries = new TimeSeries("Visits");

        // Creating TimeSeries for Views
        TimeSeries viewsSeries = new TimeSeries("Views");

        // Adding data to Visits and Views Series
        for(int i=0;i<dt.length;i++){
            visitsSeries.add(dt[i], visits[i]);
            viewsSeries.add(dt[i],views[i]);
        }

        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        // Adding Visits Series to the dataset
        dataset.addSeries(visitsSeries);

        // Adding Visits Series to dataset
        dataset.addSeries(viewsSeries);

        // Creating XYSeriesRenderer to customize visitsSeries
        XYSeriesRenderer visitsRenderer = new XYSeriesRenderer();
        visitsRenderer.setColor(Color.WHITE);
        visitsRenderer.setPointStyle(PointStyle.CIRCLE);
        visitsRenderer.setFillPoints(true);
        visitsRenderer.setLineWidth(2);
        visitsRenderer.setDisplayChartValues(true);

        // Creating XYSeriesRenderer to customize viewsSeries
        XYSeriesRenderer viewsRenderer = new XYSeriesRenderer();
        viewsRenderer.setColor(Color.YELLOW);
        viewsRenderer.setPointStyle(PointStyle.CIRCLE);
        viewsRenderer.setFillPoints(true);
        viewsRenderer.setLineWidth(2);
        viewsRenderer.setDisplayChartValues(true);


        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

        multiRenderer.setChartTitle("Visits vs Views Chart");
        multiRenderer.setXTitle("Days");
        multiRenderer.setYTitle("Count");
        multiRenderer.setZoomButtonsVisible(false);
        multiRenderer.setYLabelsAngle(-45);
        multiRenderer.setXLabelsAngle(45);

        // Adding visitsRenderer and viewsRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(visitsRenderer);
        multiRenderer.addSeriesRenderer(viewsRenderer);


        //prueba begins
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        float val = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, metrics);
        multiRenderer.setLabelsTextSize(val);
        viewsRenderer.setChartValuesTextSize(val);
        visitsRenderer.setChartValuesTextSize(val);
        //prueba ends

        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);

        // Creating a Time Chart
        mChart = (GraphicalView) ChartFactory.getTimeChartView(getBaseContext(), dataset, multiRenderer,"dd-MMM-yyyy");

        multiRenderer.setClickEnabled(true);
        multiRenderer.setSelectableBuffer(10);



        // Setting a click event listener for the graph
        mChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Format formatter = new SimpleDateFormat("dd-MMM-yyyy");

                SeriesSelection seriesSelection = mChart.getCurrentSeriesAndPoint();

                if (seriesSelection != null) {
                    int seriesIndex = seriesSelection.getSeriesIndex();
                    String selectedSeries="Visits";
                    if(seriesIndex==0)
                        selectedSeries = "Visits";
                    else
                        selectedSeries = "Views";

                    // Getting the clicked Date ( x value )
                    long clickedDateSeconds = (long) seriesSelection.getXValue();
                    Date clickedDate = new Date(clickedDateSeconds);
                    String strDate = formatter.format(clickedDate);

                    // Getting the y value
                    int amount = (int) seriesSelection.getValue();

                    // Displaying Toast Message
                    Toast.makeText(
                            getBaseContext(),
                            selectedSeries + " on "  + strDate + " : " + amount ,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Adding the Line Chart to the LinearLayout
        chartContainer.addView(mChart);
    }
}
