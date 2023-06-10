package com.chuidiang.pruebas.postconstruct;

import com.chuidiang.pruebas.spring.Persona;

/**
 * @author fjabellan 04/06/2023
 */
public class PostConstructXMLSample {

    Persona persona;
    public PostConstructXMLSample(){
        System.out.println("Instancian "+getClass() +" persona = "+persona);
    }

    public void init(){
        System.out.println("Llaman init() "+getClass() +" persona = "+persona);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
