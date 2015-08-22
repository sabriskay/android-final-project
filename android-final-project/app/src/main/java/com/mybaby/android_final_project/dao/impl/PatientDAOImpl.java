package com.mybaby.android_final_project.dao.impl;

import android.content.Context;

import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.PatientDAO;
import com.mybaby.android_final_project.model.Patient;

import java.util.Calendar;

/**
 * Created by paula.garcia on 8/10/2015.
 */
public class PatientDAOImpl implements PatientDAO{

    Context context;
    public PatientDAOImpl(Context context){
        this.context= context;
    }

    @Override
    public Patient getPatient() {
        return PediatricControlDatabaseHelper.getDatabaseInstance(context).getPatient();
    }

    @Override
    public void addPatient(String name, Calendar birthDate, int id, String genre, int idBloodGroup) {
        PediatricControlDatabaseHelper.getDatabaseInstance(context).insertPatient(name, birthDate, id, genre, idBloodGroup);
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
