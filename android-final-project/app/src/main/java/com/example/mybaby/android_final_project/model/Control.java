package com.example.mybaby.android_final_project.model;

import java.io.Serializable;

public class Control implements Serializable {

    private int id_control;
    private int id_paciente;
    private int cantidad_dientes;
    private String fecha_control;
    private String pediatra;
    private String notas;

    public Control(int id_control, int id_paciente, int cantidad_dientes, String fecha_control, String pediatra, String notas) {
        this.id_control = id_control;
        this.id_paciente = id_paciente;
        this.cantidad_dientes = cantidad_dientes;
        this.fecha_control = fecha_control;
        this.pediatra = pediatra;
        this.notas = notas;
    }

    public int getId_control() {
        return id_control;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public int getCantidad_dientes() {
        return cantidad_dientes;
    }

    public void setCantidad_dientes(int cantidad_dientes) {
        this.cantidad_dientes = cantidad_dientes;
    }

    public String getFecha_control() {
        return fecha_control;
    }

    public void setFecha_control(String fecha_control) {
        this.fecha_control = fecha_control;
    }

    public String getPediatra() {
        return pediatra;
    }

    public void setPediatra(String pediatra) {
        this.pediatra = pediatra;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
