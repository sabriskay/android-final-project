package com.example.mybaby.android_final_project.dao.impl;

import android.content.Context;

import com.example.mybaby.android_final_project.backend.ControlPediatricoDatabaseHelper;
import com.example.mybaby.android_final_project.dao.PacienteDAO;
import com.example.mybaby.android_final_project.model.Paciente;

/**
 * Created by paula.garcia on 8/10/2015.
 */
public class PacienteDAOImpl implements PacienteDAO{

    ControlPediatricoDatabaseHelper controlPediatricoDatabaseHelper;

    public PacienteDAOImpl(Context context ){

    }

    @Override
    public Paciente getPaciente(String nombre) {

        return null;
    }

    @Override
    public void updatePaciente(Paciente paciente) {

    }

    @Override
    public void deletePaciente(Paciente paciente) {

    }
}
