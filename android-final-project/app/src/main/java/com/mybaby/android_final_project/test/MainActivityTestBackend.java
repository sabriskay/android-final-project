package com.mybaby.android_final_project.test;

import android.app.Activity;
import android.os.Bundle;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.model.Control;

import junit.framework.Assert;

import java.util.List;

public class MainActivityTestBackend extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_backend);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).deleteTables();
        PediatricControlDatabaseHelper.getDatabaseInstance(this).onInitializeDB();
        PediatricControlDatabaseHelper.getDatabaseInstance(this).getAllMood();

        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient("a1", "2015-01-01", 12345678, "F", 1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).getPatient(1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-01-01", 1, 3.5f, 30.3f, 30.1f, 2, "Angela", "factor AG", 1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-03-01", 1, 5.5f, 60.6f, 70.7f, 3, "Beatriz", "volver en 15", 1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).getAllControl();

        ControlDAO controlDAOImpl= new ControlDAOImpl(this);
        List<Control> controlList = controlDAOImpl.getAllControls();
        Assert.assertEquals(controlList.size(),2);

        Control control =  controlDAOImpl.getControl(controlList.get(0).getIdControl());
        Assert.assertEquals(control.getIdControl(), 2);

        Control lastControl = controlDAOImpl.getLastControl();
        Assert.assertEquals(lastControl.getIdControl(), 2);

        int deleted = controlDAOImpl.deleteControl(lastControl.getIdControl());
        Assert.assertEquals(deleted, 1);

        controlList = controlDAOImpl.getAllControls();
        Assert.assertEquals(controlList.size(),1);


    }

}
