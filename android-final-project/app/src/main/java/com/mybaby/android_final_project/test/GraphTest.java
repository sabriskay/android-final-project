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
import com.mybaby.android_final_project.datatables.LengthForAgeInfantCharts;

public class GraphTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_test);
        GraphView graph = (GraphView) findViewById(R.id.graph);

        for (int i= 1;i < 8 ; i++) {
            graph.addSeries(getDataPointLineGraphSeries(i));
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
    private LineGraphSeries<DataPoint> getDataPointLineGraphSeries(int column) {
        DataPoint[] data = new DataPoint[LengthForAgeInfantCharts.MONTHS.length];

        for (int i = 0; i < data.length; i++) {
            int value = i * 8;
            double x = LengthForAgeInfantCharts.LENGTH_FOR_AGE_INFANT_BOYS_REFERENCES[0 + value];
            double y = LengthForAgeInfantCharts.LENGTH_FOR_AGE_INFANT_BOYS_REFERENCES[column + value];
            data[i] = new DataPoint(x, y);
        }

        return new LineGraphSeries<DataPoint>(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graph_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
