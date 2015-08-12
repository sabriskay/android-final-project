package com.mybaby.android_final_project.test;

import android.app.Activity;
import android.os.Bundle;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PedriatricControlDatabaseHelper;
import com.mybaby.android_final_project.model.Patient;

public class MainActivityTestBackend extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_backend);
        PedriatricControlDatabaseHelper.getDatabaseInstance(this).deleteTables();
        PedriatricControlDatabaseHelper.getDatabaseInstance(this).onInitializeDB();
        PedriatricControlDatabaseHelper.getDatabaseInstance(this).getAllMood();

        PedriatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient("a1", "2015-01-01", 12345678, "F", 1);
        PedriatricControlDatabaseHelper.getDatabaseInstance(this).getPatient("a1");
        PedriatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-01-01", 1, 3.5f, 30.3f, 30.1f, 2, "Angela", "factor AG", 1);
        PedriatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-03-01", 1, 5.5f, 60.6f, 70.7f, 3, "Beatriz", "volver en 15", 1);
        PedriatricControlDatabaseHelper.getDatabaseInstance(this).getAllControl();
        PedriatricControlDatabaseHelper.getDatabaseInstance(this).getControl(2);
    }

}
