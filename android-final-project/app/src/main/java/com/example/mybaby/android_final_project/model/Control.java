package com.example.mybaby.android_final_project.model;

import java.io.Serializable;
import java.util.Calendar;

public class Control implements Serializable {

    private int id_control;
    private int id_patient;
    private int tooth_amount;
    private Calendar date_control;
    private float weight;
    private float height;
    private float head_circumference;
    private String pediatrician;
    private String notes;
    private Mood mood;

    public Control(int id_control, int id_patient, int tooth_amount, Calendar date_control, float weight, float height, float head_circumference, String pediatrician, String notes) {
        this.id_control = id_control;
        this.id_patient = id_patient;
        this.tooth_amount = tooth_amount;
        this.date_control = date_control;
        this.weight = weight;
        this.height = height;
        this.head_circumference = head_circumference;
        this.pediatrician = pediatrician;
        this.notes = notes;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }
}