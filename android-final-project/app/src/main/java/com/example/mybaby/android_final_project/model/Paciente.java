package com.example.mybaby.android_final_project.model;

import java.util.Calendar;

/**
 * Created by Paula on 10/08/2015.
 */
public class Paciente {
    private int id_paciente;
    private String nombre;
    private Calendar fecha_nac;
    private int dni;
    private String sexo;
    private int id_grupo_sanguineo;


    public Paciente(int id_paciente, String nombre, Calendar fecha_nac, int dni, String sexo, int id_grupo_sanguineo) {
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.fecha_nac = fecha_nac;
        this.dni = dni;
        this.sexo = sexo;
        this.id_grupo_sanguineo = id_grupo_sanguineo;
    }

    public Paciente(){}


    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Calendar getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Calendar fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getId_grupo_sanguineo() {
        return id_grupo_sanguineo;
    }

    public void setId_grupo_sanguineo(int id_grupo_sanguineo) {
        this.id_grupo_sanguineo = id_grupo_sanguineo;
    }
}
