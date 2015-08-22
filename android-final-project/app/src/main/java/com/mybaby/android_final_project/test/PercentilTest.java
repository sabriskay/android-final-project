package com.mybaby.android_final_project.test;

import android.app.Activity;
import android.os.Bundle;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.PatientDAO;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.dao.impl.PatientDAOImpl;
import com.mybaby.android_final_project.model.Control;
import com.mybaby.android_final_project.model.Patient;

import junit.framework.Assert;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class PercentilTest extends Activity {

    PatientDAO patientDAOImpl = null;
    ControlDAO controlDAOImpl = null;
    Patient patient = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_percentil);

        PediatricControlDatabaseHelper.getDatabaseInstance(this).deleteTables();
        PediatricControlDatabaseHelper.getDatabaseInstance(this).onInitializeDB();

        Patient patientA1 = new Patient(1, "a1", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertStringToCalendar("2015-01-01"), 12345678, "F", 1);
        Patient patientA2 = new Patient(2, "A2", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertStringToCalendar("2015-09-01"), 87654321, "M", 2);

        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient(patientA1);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient(patientA2);
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-01-01", 1, 3.5f, 30.3f, 30.1f, 2, "Angela", "factor AG", "triste");
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-02-01", 1, 5.5f, 60.6f, 70.7f, 3, "Beatriz", "volver en 15", "contento");
        PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl("2015-03-01", 1, 7.5f, 90.6f, 90.7f, 3, "Beatriz", "volver en 1 mes", "contento");


        // Test Control
        controlDAOImpl= new ControlDAOImpl(this);
        List<Control> controlList = controlDAOImpl.getAllControls("asc");
        Assert.assertEquals(controlList.size(),3);

        // Test Patient
        patientDAOImpl = new PatientDAOImpl(this);
        patient = patientDAOImpl.getPatient(controlList.get(0).getIdPatient());
        Assert.assertEquals(12345678, patient.getid());

        Patient patientToCheckBirthday = patientDAOImpl.getPatient(2);
        Assert.assertEquals("2015-09-01", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertCalendarToString(patientToCheckBirthday.getBirthDate()));

        // Create array of weith per month
        Assert.assertEquals(monthsBetween(patient.getBirthDate(), controlList.get(0).getDateControl()),0);
        Assert.assertEquals(monthsBetween(patient.getBirthDate(), controlList.get(1).getDateControl()),1);
        Assert.assertEquals(monthsBetween(patient.getBirthDate(), controlList.get(2).getDateControl()),2);

        HashMap weightPerMonth = new HashMap();
        HashMap heightPerMonth = new HashMap();
        for (int i =0; i < controlList.size(); i++){
            int x= monthsBetween(patient.getBirthDate(), controlList.get(i).getDateControl());
            weightPerMonth.put(x, controlList.get(i).getWeight());
            heightPerMonth.put(x,controlList.get(i).getHeight());
        }

        Assert.assertEquals(weightPerMonth.size(),3);
    }

    /**
     *
     * @param birthDate
     * @param controlDate
     * @return amount of months between dates
     */
    public int monthsBetween(Calendar birthDate, Calendar controlDate){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(birthDate.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(controlDate.getTime());
        int diff = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return diff;
    }
}