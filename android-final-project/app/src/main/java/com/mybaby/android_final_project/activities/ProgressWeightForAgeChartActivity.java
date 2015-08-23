package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.commons.CustomMultiRenderer;
import com.mybaby.android_final_project.commons.ProgressChartActivity;
import com.mybaby.android_final_project.commons.Utils;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.PatientDAO;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.dao.impl.PatientDAOImpl;
import com.mybaby.android_final_project.datatables.LengthForAgeInfantCharts;
import com.mybaby.android_final_project.datatables.WeightForAgeInfantCharts;
import com.mybaby.android_final_project.model.Control;
import com.mybaby.android_final_project.model.MeasurePerMonth;
import com.mybaby.android_final_project.model.Patient;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
        patient = new Patient(1, "a1", PediatricControlDatabaseHelper.getDatabaseInstance(this).convertStringToCalendar("2015-01-01"), 12345678, "M", 1);
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
            weightPerMonth.add(new MeasurePerMonth(x, controlList.get(i).getWeight()));
        }
        Collections.sort(weightPerMonth);
        return weightPerMonth;
    }

}
