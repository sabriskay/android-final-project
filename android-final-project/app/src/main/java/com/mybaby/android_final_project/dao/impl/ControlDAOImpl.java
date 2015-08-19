package com.mybaby.android_final_project.dao.impl;

import android.content.Context;

import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.PatientDAO;
import com.mybaby.android_final_project.model.Control;
import com.mybaby.android_final_project.model.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Paula on 12/08/2015.
 */
public class ControlDAOImpl implements ControlDAO {

    private Context context;

    public ControlDAOImpl(Context context){
        this.context = context;
    }

    @Override
    public void addControl(Control Control) {
        PediatricControlDatabaseHelper.getDatabaseInstance(context).insertControl(convertCalendarToString(Control.getDateControl()), Control.getIdPatient(), Control.getWeight(),
                Control.getHeight(), Control.getHeadCircumference(), Control.getTeethAmount(), Control.getPediatrician(),
                Control.getNotes(), Control.getMood().toLowerCase().trim());

    }

    @Override
    public Control getControl(int id) {
        return  PediatricControlDatabaseHelper.getDatabaseInstance(context).getControl(id);
    }

    @Override
    public List<Control> getAllControls() {
        return  PediatricControlDatabaseHelper.getDatabaseInstance(context).getAllControl();
    }

    @Override
    public int getControlsCount() {
        return 0;
    }

    @Override
    public int updateControl(Control Control) {

        return 0;
    }

    @Override
    public int deleteControl(int idControl) {
        return PediatricControlDatabaseHelper.getDatabaseInstance(context).deleteControl(idControl);
    }

    @Override
    public Control getLastControl() {
        return PediatricControlDatabaseHelper.getDatabaseInstance(context).getLastControl();
    }

    public String convertCalendarToString(Calendar date) {
        String strdate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        if (date != null) {
            strdate = sdf.format(date.getTime());
        }
        return strdate;
    }


    public Calendar convertStringToCalendar(String date){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
}