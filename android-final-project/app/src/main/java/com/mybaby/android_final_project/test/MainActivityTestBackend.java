package com.mybaby.android_final_project.test;

import android.app.Activity;
import android.os.Bundle;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.BloodGroupDAO;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.PatientDAO;
import com.mybaby.android_final_project.dao.impl.BloodGroupDAOImpl;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.dao.impl.PatientDAOImpl;
import com.mybaby.android_final_project.model.BloodGroup;
import com.mybaby.android_final_project.model.Control;
import com.mybaby.android_final_project.model.Patient;

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

        Patient patientA1 = new Patient(1, "a1", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertStringToCalendar("2015-01-01"), 12345678, "F", 1);
        Patient patientA2 = new Patient(2, "A2", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertStringToCalendar("2015-09-01"), 87654321, "M", 2);

        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient(patientA1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient(patientA2);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-01-01", 1, 3.5f, 30.3f, 30.1f, 2, "Angela", "factor AG", 1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-03-01", 1, 5.5f, 60.6f, 70.7f, 3, "Beatriz", "volver en 15", 1);

        // Test Patient
        PatientDAO patientDAOImpl = new PatientDAOImpl(this);
        Patient patient = patientDAOImpl.getPatient(1);
        Assert.assertEquals(12345678, patient.getid());

        Patient patientToUpdate = patientDAOImpl.getPatient(2);
        patientToUpdate.setName("A3");
        PediatricControlDatabaseHelper.getDatabaseInstance(this).updatePatient(patientToUpdate);
        Patient patientUpdated = patientDAOImpl.getPatient(2);
        Assert.assertEquals("A3", patientUpdated.getName());

        Patient patientToCheckBirthday = patientDAOImpl.getPatient(2);
        Assert.assertEquals("2015-09-01", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertCalendarToString(patientToCheckBirthday.getBirthDate()));

        PediatricControlDatabaseHelper.getDatabaseInstance(this).deletePatient(2);
        Patient patientDeleted = patientDAOImpl.getPatient(2);
        Assert.assertNull(patientDeleted);

        // Test Control
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

        // Test Blood Group
        BloodGroupDAO bloodGroupDAOImpl = new BloodGroupDAOImpl(this);
        List<BloodGroup> bloodGroupList = bloodGroupDAOImpl.getAllBloodGroup();
        Assert.assertEquals(8, bloodGroupList.size());
    }

}
