package com.mybaby.android_final_project.commons;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import org.achartengine.renderer.XYMultipleSeriesRenderer;

/**
 * Created by Paula on 23/08/2015.
 */
public class CustomMultiRenderer extends XYMultipleSeriesRenderer {
    private float val;

    public CustomMultiRenderer(Context context, String title, String yAxisLabel){
        super();
        this.setChartTitle(title);
        this.setXTitle("Months");
        this.setYTitle(yAxisLabel);
        this.setZoomButtonsVisible(false);
        this.setYLabelsAngle(-45);
        this.setXLabelsAngle(45);
        this.setXAxisMin(0);
        this.setXAxisMax(24);
        //this.setYAxisMin(0);
       // this.setYAxisMax(20);
        this.setZoomEnabled(false);
        this.setPanEnabled(false);

        //prueba begins
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        this.val = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, metrics);
        this.setLabelsTextSize(getVal());
        this.setChartTitleTextSize(getVal());
        this.setAxisTitleTextSize(getVal());
        this.setShowLegend(false);

    }


    public float getVal() {
        return val;
    }
}
