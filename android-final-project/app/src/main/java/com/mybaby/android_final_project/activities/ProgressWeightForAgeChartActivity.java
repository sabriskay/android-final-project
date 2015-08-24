package com.mybaby.android_final_project.activities;

import android.os.Bundle;

import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.commons.ProgressChartActivity;
import com.mybaby.android_final_project.commons.Utils;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.datatables.WeightForAgeInfantCharts;
import com.mybaby.android_final_project.model.Control;
import com.mybaby.android_final_project.model.MeasurePerMonth;
import com.mybaby.android_final_project.model.Patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProgressWeightForAgeChartActivity extends ProgressChartActivity {

    public static final String GENDER = "M";
    public static final String WEIGHT = "Weight";
    Patient patient = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setGender(getIntent().getStringExtra("gender"));
        setGender(GENDER);
        patient = PediatricControlDatabaseHelper.getDatabaseInstance(this).getCurrentPatient();
        setBabyMeasures(getBabyAndControlData());
        setMeasureTableTitle(WEIGHT);
        decideStardardChart();
        openChart();
    }

    private void decideStardardChart() {
        if (getGender().equals(GENDER)) {
            setStandardChart(WeightForAgeInfantCharts.WEIGHT_FOR_AGE_INFANT_BOYS_REFERENCES);
        } else {
            setStandardChart(WeightForAgeInfantCharts.WEIGHT_FOR_AGE_INFANT_GIRLS_REFERENCES);
        }
    }

    private List<MeasurePerMonth> getBabyAndControlData() {
        ControlDAO controlDAOImpl = new ControlDAOImpl(this);
        List<Control> controlList = controlDAOImpl.getAllControls();
        List<MeasurePerMonth> weightPerMonth = new ArrayList<MeasurePerMonth>();

        for (int i = 0; i < controlList.size(); i++) {
            int x = Utils.monthsBetween(patient.getBirthDate(), controlList.get(i).getDateControl());
            weightPerMonth.add(new MeasurePerMonth(x,controlList.get(i).getWeight()));
        }
        Collections.sort(weightPerMonth);
        return weightPerMonth;
    }

}
