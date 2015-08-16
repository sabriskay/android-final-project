package com.mybaby.android_final_project.dao.impl;

import android.content.Context;

import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.BloodGroupDAO;
import com.mybaby.android_final_project.model.BloodGroup;

import java.util.List;

/**
 * Created by SabrinaKay on 8/15/15.
 */

public class BloodGroupDAOImpl implements BloodGroupDAO {
    private Context context;

    public BloodGroupDAOImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<BloodGroup> getAllBloodGroup() {
        return PediatricControlDatabaseHelper.getDatabaseInstance(context).getAllBloodType();
    }
}
