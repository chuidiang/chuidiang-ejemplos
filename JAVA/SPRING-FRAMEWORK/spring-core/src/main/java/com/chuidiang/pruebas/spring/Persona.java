package com.chuidiang.pruebas.spring;

import java.util.Date;

/**
 * @author fjabellan 28/05/2023
 */

public class Persona {
    int id;
    String nombre;
    Date fechaNacimiento;

    public Persona(){
        // Constructor sin parametros.
    }

    public Persona(int id, String nombre, Date fechaNacimiento){
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /** Para poder escribir el bean en pantalla de forma rápida */
    public String toString()
    {
        return ""+id+" "+nombre+" "+fechaNacimiento;
    }
}