package com.chuidiang.examples.proguard_library.internal;

import com.chuidiang.examples.proguard_library.shared.IfzPublic;

/**
 * Clase privada que implementa interfaz publica.
 * Proguard debe mantener el método publico.
 * @author fjabellan
 * @date 31/10/2020
 */
public class PrivateClass implements IfzPublic {
    @Override
    public double add(double number1, double number2) {
        return internalAdd(number1,number2);
    }

    private double internalAdd(double number1, double number2){
        return number1+number2;
    }
}
