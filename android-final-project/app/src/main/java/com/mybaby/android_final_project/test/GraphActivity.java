package com.mybaby.android_final_project.test;

/**
 * Created by Paula on 19/08/2015.
 */

import android.app.Activity;
import android.os.Bundle;

public class GraphActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float[] values = new float[] { 2.0f,1.5f, 2.5f, 1.0f , 3.0f };
        String[] verlabels = new String[] { "great", "ok", "bad" };
        String[] horlabels = new String[] { "today", "tomorrow", "next week", "next month" };
        GraphView graphView = new GraphView(this, values, "GraphViewDemo",horlabels, verlabels, GraphView.BAR);
        setContentView(graphView);
    }
}