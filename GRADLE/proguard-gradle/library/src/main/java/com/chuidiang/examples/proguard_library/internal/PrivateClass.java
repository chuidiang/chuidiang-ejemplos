package com.chuidiang.examples.proguard_library.internal;

import com.chuidiang.examples.proguard_library.shared.IfzPublic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase privada que implementa interfaz publica.
 * Proguard debe mantener el método publico.
 * @author fjabellan
 * @date 31/10/2020
 */
public class PrivateClass implements IfzPublic {
    private static final Logger log = LoggerFactory.getLogger(PrivateClass.class);

    @Override
    public double add(double number1, double number2) {
        return internalAdd(number1,number2);
    }

    private double internalAdd(double number1, double number2){
        log.info("Toy sumando números modificados un poquito");
        return number1+number2;
    }
}
