package com.mybaby.android_final_project.dao.impl;

import android.content.Context;

import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.model.Mood;

import java.util.List;

/**
 * Created by paula.garcia on 8/10/2015.
 */
public class MoodDAOImpl {

    private PediatricControlDatabaseHelper pediatricControlDatabaseHelper;

    public MoodDAOImpl(Context context){

    }

    public List<Mood> getAllMoods() {
        List<Mood> mood = pediatricControlDatabaseHelper.getAllMood();
        return mood;
    }
}
