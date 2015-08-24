package com.mybaby.android_final_project.commons;

import android.content.Context;
import android.graphics.Color;
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
        this.setXAxisMin(0);
        this.setXAxisMax(24);
        this.setZoomEnabled(false);
        this.setPanEnabled(false);
        this.setShowLegend(false);

        //size
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        this.val = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, metrics);
        this.setLabelsTextSize(getVal());
        this.setChartTitleTextSize(getVal()* 1.5f);
        this.setAxisTitleTextSize(getVal() * 1.2f);

        //Color
        this.setApplyBackgroundColor(true);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setMarginsColor(Color.parseColor("#269983"));
        this.setAxesColor(Color.parseColor("#269983"));
        this.setLabelsColor(Color.WHITE);
        this.setShowGrid(true);
        this.setGridColor(Color.parseColor("#269983"));
        this.setMargins(new int[]{80, 60, 10, 60});

    }


    public float getVal() {
        return val;
    }
}
