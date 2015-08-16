package com.mybaby.android_final_project.dao;

import com.mybaby.android_final_project.model.Patient;

/**
 * Created by paula.garcia on 8/10/2015.
 */
public interface PatientDAO {

    public Patient getPatient(int idPatient);
    public void addPatient(Patient patient);
    public void updatePatient(Patient patient);
    public void deletePatient(int idPatient);
}
