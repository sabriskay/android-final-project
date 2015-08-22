package com.mybaby.android_final_project.dao;

import com.mybaby.android_final_project.model.Patient;

import java.util.Calendar;

/**
 * Created by paula.garcia on 8/10/2015.
 */
public interface PatientDAO {

    public Patient getPatient();
    public void addPatient(String name, Calendar birthDate, int id, String genre, int idBloodGroup);
    public void updatePatient(Patient patient);
    public void deletePatient(int idPatient);
}
