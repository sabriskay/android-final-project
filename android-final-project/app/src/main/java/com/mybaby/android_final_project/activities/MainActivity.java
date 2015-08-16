package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mybaby.android_final_project.R;

/**
 * Created by SabrinaKay on 08/08/2015.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void goToViewProfile(View v) {

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goToViewNewControl(View v) {

        Intent intent = new Intent(this, AddNewControlActivity.class);
        startActivity(intent);
    }

    public void goToViewProgress(View v) {

        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }

    public void goToViewControlsHistory(View v) {

        Intent intent = new Intent(this, ControlHistoryActivity.class);
        startActivity(intent);
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
