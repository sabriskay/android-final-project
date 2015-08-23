package com.mybaby.android_final_project.activities;

import android.os.Bundle;

import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.commons.ProgressChartActivity;
import com.mybaby.android_final_project.commons.Utils;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.datatables.LengthForAgeInfantCharts;
import com.mybaby.android_final_project.model.Control;
import com.mybaby.android_final_project.model.MeasurePerMonth;
import com.mybaby.android_final_project.model.Patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProgressLengthForAgeChartActivity extends ProgressChartActivity {

    public static final String LENGTH = "Length";
    public static final String GENDER = "M";
    Patient patient = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setGender(getIntent().getStringExtra("gender"));
        setGender(GENDER);
        patient = new Patient(1, "a1", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertStringToCalendar("2015-01-01"), 12345678, "M", 1);
        setMeasureTableTitle(LENGTH);
        setBabyMeasures(getBabyAndControlData());
        decideStardardChart();
        openChart();
    }

    private void decideStardardChart() {

        if (getGender().equals(GENDER)) {
            setStandardChart(LengthForAgeInfantCharts.LENGTH_FOR_AGE_INFANT_BOYS_REFERENCES);
        } else {
            setStandardChart(LengthForAgeInfantCharts.LENGTH_FOR_AGE_INFANT_GIRLS_REFERENCES);
        }
    }


    private List<MeasurePerMonth> getBabyAndControlData() {

        ControlDAO controlDAOImpl = new ControlDAOImpl(this);
        List<Control> controlList = controlDAOImpl.getAllControls();
        List<MeasurePerMonth> heightPerMonth = new ArrayList<MeasurePerMonth>();

        for (int i = 0; i < controlList.size(); i++) {
            int x = Utils.monthsBetween(patient.getBirthDate(), controlList.get(i).getDateControl());
            heightPerMonth.add(new MeasurePerMonth(x, controlList.get(i).getHeight()));
        }
        Collections.sort(heightPerMonth);
        return heightPerMonth;
    }


}
