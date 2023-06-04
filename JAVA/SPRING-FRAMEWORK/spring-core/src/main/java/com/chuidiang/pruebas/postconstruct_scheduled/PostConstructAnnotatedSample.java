package com.chuidiang.pruebas.postconstruct_scheduled;

import com.chuidiang.pruebas.spring.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author fjabellan 04/06/2023
 */
@Component
public class PostConstructAnnotatedSample {

    @Autowired
    Persona persona;
    public PostConstructAnnotatedSample(){
        System.out.println("Instancian "+getClass() +" persona = "+persona);
    }
    @PostConstruct
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
