package com.mybaby.android_final_project.model;

/**
 * Created by Paula on 22/08/2015.
 */
public class MeasurePerMonth implements Comparable<MeasurePerMonth>{

    private int month;
    private float measure;

    public MeasurePerMonth( int month,  float measure){
        this.month = month;
        this.measure = measure;
    }

    @Override
    public int compareTo(MeasurePerMonth another) {
        if (another.getMonth() > this.getMonth()){
            return -1;
        }
        return 1;
    }

    public int getMonth() {
        return month;
    }

    public double getMeasure() {
        return measure;
    }
}
