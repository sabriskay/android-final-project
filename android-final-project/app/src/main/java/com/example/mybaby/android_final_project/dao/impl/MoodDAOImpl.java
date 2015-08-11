package com.example.mybaby.android_final_project.dao.impl;

import android.content.Context;

import com.example.mybaby.android_final_project.backend.PedriatricControlDatabaseHelper;
import com.example.mybaby.android_final_project.dao.PatientDAO;
import com.example.mybaby.android_final_project.model.Mood;
import com.example.mybaby.android_final_project.model.Patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paula.garcia on 8/10/2015.
 */
public class MoodDAOImpl {

    private PedriatricControlDatabaseHelper pedriatricControlDatabaseHelper;

    public MoodDAOImpl(Context context){

    }

    public List<Mood> getAllMoods() {
        List<Mood> mood = pedriatricControlDatabaseHelper.getAllMood();
        return mood;
    }
}
