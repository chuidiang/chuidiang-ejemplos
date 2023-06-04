package com.chuidiang.pruebas.annotated_spring;

import com.chuidiang.pruebas.spring.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author fjabellan 03/06/2023
 */
@Component
// @Component("ListaDePepe")  // Se puede poner un nombre a la instancia.
public class ListaContactos {
    @Autowired
//  @Autowired(required = false)  // Se puede indicar que es posible que no haya ninguna instancia de Persona
                                  // y que el código no debe fallar en ese caso. Por defecto, required es true
//  @Qualifier("Pedro")  // Se pueden pedir instancias con un nombre o id concreto, Pedro en este ejemplo.
    private List<Persona> contactos;

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
