package com.mybaby.android_final_project.model;

import java.io.Serializable;
import java.util.Calendar;

public class Control implements Serializable {

    private int idControl;
    private int idPatient;
    private int teethAmount;
    private Calendar dateControl;
    private float weight;
    private float height;
    private float headCircumference;
    private String pediatrician;
    private String notes;
    private int idMood;
    private Mood mood;

    public Control(int idControl, int idPatient, int teeth_amount, Calendar date_control, float weight, float height, float headCircumference, String pediatrician, String notes, int idMood) {
        this.idControl = idControl;
        this.idPatient = idPatient;
        this.teethAmount = teeth_amount;
        this.dateControl = date_control;
        this.weight = weight;
        this.height = height;
        this.headCircumference = headCircumference;
        this.pediatrician = pediatrician;
        this.notes = notes;
        this.idMood = idMood;
    }

    public int getIdMood() {
        return idMood;
    }

    public void setIdMood(int idMood) {
        this.idMood = idMood;
    }

    public int getIdControl() {
        return idControl;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getTeethAmount() {
        return teethAmount;
    }

    public void setTeethAmount(int teethAmount) {
        this.teethAmount = teethAmount;
    }

    public Calendar getDateControl() {
        return dateControl;
    }

    public void setDateControl(Calendar dateControl) {
        this.dateControl = dateControl;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(float headCircumference) {
        this.headCircumference = headCircumference;
    }

    public String getPediatrician() {
        return pediatrician;
    }

    public void setPediatrician(String pediatrician) {
        this.pediatrician = pediatrician;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setIdControl(int idControl) {
        this.idControl = idControl;
    }
}