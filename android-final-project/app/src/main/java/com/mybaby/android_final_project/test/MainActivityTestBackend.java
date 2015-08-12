package com.mybaby.android_final_project.test;

import android.app.Activity;
import android.os.Bundle;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;

public class MainActivityTestBackend extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_backend);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).deleteTables();
        PediatricControlDatabaseHelper.getDatabaseInstance(this).onInitializeDB();
        PediatricControlDatabaseHelper.getDatabaseInstance(this).getAllMood();

        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient("a1", "2015-01-01", 12345678, "F", 1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).getPatient("a1");
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-01-01", 1, 3.5f, 30.3f, 30.1f, 2, "Angela", "factor AG", 1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-03-01", 1, 5.5f, 60.6f, 70.7f, 3, "Beatriz", "volver en 15", 1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).getAllControl();
        PediatricControlDatabaseHelper.getDatabaseInstance(this).getControl(2);
    }

}
