package com.chuidiang.pruebas.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fjabellan 28/05/2023
 */
public class Agenda {
    private Persona propietarioAgenda;
    private List<Persona> contactos;

    public String toString(){
        return "Propietario: "+propietarioAgenda+"\nContactos: "+ Arrays.toString(contactos.toArray());
    }
    public Persona getPropietarioAgenda() {
        return propietarioAgenda;
    }

    public void setPropietarioAgenda(Persona propietarioAgenda) {
        this.propietarioAgenda = propietarioAgenda;
    }

    public List<Persona> getContactos() {
        return contactos;
    }

    public void setContactos(List<Persona> contactos) {
        this.contactos = contactos;
    }
}
