package com.mybaby.android_final_project.dao;

import com.mybaby.android_final_project.model.Control;

import java.util.List;

/**
 * Created by Paula on 10/08/2015.
 */
public interface ControlDAO {
    // Adding new Control
    public void addControl(Control Control);

    // Getting single Control
    public Control getControl(int id);

    // Getting All Controls Desc by default
    public List<Control> getAllControls();

    // Getting Controls Count
    public int getControlsCount();

    // Updating single Control
    public int updateControl(Control Control);

    // Deleting single Control
    public int deleteControl(int id);


    // Getting single Last Control
    public Control getLastControl();

    //By order
    public List<Control> getAllControls(String orderBy);
}
