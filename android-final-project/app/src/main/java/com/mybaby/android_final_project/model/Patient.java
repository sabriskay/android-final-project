package com.mybaby.android_final_project.model;

import java.util.Calendar;

/**
 * Created by Paula on 10/08/2015.
 */
public class Patient {
    private int idPatient;
    private String name;
    private Calendar birthDate;
    private int id;
    private String genre;
    private int idBloodGroup;


    public Patient(int idPatient, String name, Calendar birthDate, int id, String genre, int idBloodGroup) {
        this.idPatient = idPatient;
        this.name = name;
        this.birthDate = birthDate;
        this.id = id;
        this.genre = genre;
        this.idBloodGroup = idBloodGroup;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public int getid() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getIdBloodGroup() {
        return idBloodGroup;
    }

    public void setIdBloodGroup(int idBloodGroup) {
        this.idBloodGroup =idBloodGroup ;
    }
}
