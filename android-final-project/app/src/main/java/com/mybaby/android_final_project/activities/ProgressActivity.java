package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mybaby.android_final_project.R;

public class ProgressActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
    }

    public void goToWightProgress(View v) {
        Intent intent = new Intent(this, ProgressWeightForAgeChartActivity.class);
        startActivity(intent);
    }

    public void goToLengthProgress(View v) {
        Intent intent = new Intent(this, ProgressLengthForAgeChartActivity.class);
        startActivity(intent);
    }

}
