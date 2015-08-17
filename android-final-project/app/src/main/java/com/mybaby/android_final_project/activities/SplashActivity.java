package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.model.Patient;


/**
 * Created by SabrinaKay on 08/08/2015.
 */
public class SplashActivity extends Activity {
    private static long SLEEP_TIME = 3;    // Sleep for some time

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadDataBase();

        new IntentLauncher().start();
    }

    private void loadDataBase() {
        // BEGIN - Create hardcode data for test purpose ONLY
        PediatricControlDatabaseHelper.getDatabaseInstance(this).deleteTables();
        PediatricControlDatabaseHelper.getDatabaseInstance(this).onInitializeDB();

        Patient patientA1 = new Patient(1, "a1", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertStringToCalendar("2015-01-01"), 12345678, "F", 1);
        Patient patientA2 = new Patient(2, "A2", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertStringToCalendar("2015-09-01"), 87654321, "M", 2);

        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient(patientA1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient(patientA2);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-01-01", 1, 3.5f, 30.3f, 30.1f, 2, "Angela", "factor AG", 1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-03-01", 1, 5.5f, 60.6f, 70.7f, 3, "Beatriz", "volver en 15", 1);
        // END - Create hardcode data for test purpose
    }

    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(SLEEP_TIME * 1000);
            } catch (Exception e) {
                Log.e(SplashActivity.class.getName(), e.getMessage());
            }

            // Start main activity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}
