package com.example.mybaby.android_final_project.dao;

import com.example.mybaby.android_final_project.model.Paciente;

import java.util.List;

/**
 * Created by paula.garcia on 8/10/2015.
 */
public interface PacienteDAO {

    public Paciente getPaciente(String nombre);
  //  public void addPaciente(String nombre , String fecha_nac, int DNI, String sexo, int id_grupo_sanguineo);
    public void updatePaciente(Paciente paciente);
    public void deletePaciente(Paciente paciente);
}
