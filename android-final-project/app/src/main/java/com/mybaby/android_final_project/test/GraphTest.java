package com.mybaby.android_final_project.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.datatables.WeightForAgeInfantCharts;

public class GraphTest extends AppCompatActivity {

    public static final int DATA_SIZE = 8;
    public static final int MONTHS = 24;
    public static final int FIRST_COLUMN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_test);
        GraphView graph = (GraphView) findViewById(R.id.graph);

      /*  for (int i= 1;i < DATA_SIZE; i++) {
            graph.addSeries(getDataPointLineGraphSeries(i,LengthForAgeInfantCharts.LENGTH_FOR_AGE_INFANT_BOYS_REFERENCES));
        }*/

        for (int i= 1;i < DATA_SIZE; i++) {
            graph.addSeries(getDataPointLineGraphSeries(i, WeightForAgeInfantCharts.WEIGHT_FOR_AGE_INFANT_GIRLS_REFERENCES));
        }

/*
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);*/
    }

    @NonNull
    private LineGraphSeries<DataPoint> getDataPointLineGraphSeries(int column, Double[] chart ) {
        DataPoint[] data = new DataPoint[MONTHS];

        for (int i = 0; i < data.length; i++) {
            int value = i * DATA_SIZE;
            double x = chart[FIRST_COLUMN + value];
            double y = chart[column + value];
            data[i] = new DataPoint(x, y);
        }

        return new LineGraphSeries<DataPoint>(data);
    }

}
