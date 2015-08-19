package com.mybaby.android_final_project.dao.impl;

import android.content.Context;

import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.BloodTypeDAO;
import com.mybaby.android_final_project.model.BloodType;

import java.util.List;

/**
 * Created by SabrinaKay on 8/15/15.
 */

public class BloodTypeDAOImpl implements BloodTypeDAO {
    private Context context;

    public BloodTypeDAOImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<BloodType> getAllBloodType() {
        return PediatricControlDatabaseHelper.getDatabaseInstance(context).getAllBloodType();
    }
}
