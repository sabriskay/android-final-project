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
    Context context;
    public PatientDAOImpl(Context context){
        context= context;
    }

    @Override
    public Patient getPatient(int idPatient) {
        Patient patient = pediatricControlDatabaseHelper.getPatient(idPatient);
        return null;
    }

    @Override
    public void addPatiente(String nombre, String fecha_nac, int DNI, String sexo, int id_grupo_sanguineo) {

    }

    @Override
    public void updatePatient(Patient patient) {

    }

    @Override
    public void deletePatient(Patient patient) {

    }
}
