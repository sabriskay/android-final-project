package com.mybaby.android_final_project.dao.impl;

import android.content.Context;

import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.PatientDAO;
import com.mybaby.android_final_project.model.Patient;

/**
 * Created by paula.garcia on 8/10/2015.
 */
public class PatientDAOImpl implements PatientDAO{

    PediatricControlDatabaseHelper pediatricControlDatabaseHelper;

    public PatientDAOImpl(Context context){

    }

    @Override
    public Patient getPatient(String name) {
        Patient patient = pediatricControlDatabaseHelper.getPatient(name);
        return null;
    }

    @Override
    public void updatePatient(Patient patient) {

    }

    @Override
    public void deletePatient(Patient patient) {

    }
}
