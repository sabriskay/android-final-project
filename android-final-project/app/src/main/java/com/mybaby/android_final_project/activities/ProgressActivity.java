package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mybaby.android_final_project.R;

public class ProgressActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_progress, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
        }

        return false;
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
