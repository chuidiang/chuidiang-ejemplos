package com.chuidiang.pruebas.annotated_spring;

import com.chuidiang.pruebas.spring.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fjabellan 03/06/2023
 */
@Component
public class ListaContactos {
    @Autowired
    private List<Persona> contactos = new ArrayList<>();

    public String toString(){
        return "Contactos: "+ Arrays.toString(contactos.toArray());
    }

    public List<Persona> getContactos() {
        return contactos;
    }

    public void setContactos(List<Persona> contactos) {
        this.contactos = contactos;
    }

}
