package com.mybaby.android_final_project.dao.impl;

import android.content.Context;

import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.PatientDAO;
import com.mybaby.android_final_project.model.Patient;

/**
 * Created by paula.garcia on 8/10/2015.
 */
public class PatientDAOImpl implements PatientDAO{

    Context context;
    public PatientDAOImpl(Context context){
        this.context= context;
    }

    @Override
    public Patient getPatient(int idPatient) {
        return PediatricControlDatabaseHelper.getDatabaseInstance(context).getPatient(idPatient);
    }

    @Override
    public void addPatient(Patient patient) {
        PediatricControlDatabaseHelper.getDatabaseInstance(context).insertPatient(patient);
    }

    @Override
    public void updatePatient(Patient patient) {
        PediatricControlDatabaseHelper.getDatabaseInstance(context).updatePatient(patient);
    }

    @Override
    public void deletePatient(int idPatient) {
        PediatricControlDatabaseHelper.getDatabaseInstance(context).deletePatient(idPatient);
    }
}
