package com.chuidiang.examples.proguard_library.shared;

import com.chuidiang.examples.proguard_library.internal.PrivateClass;

/**
 * @author fjabellan
 * @date 01/11/2020
 */
public class PublicClass {

    // Metodos de diversos tipos para ver como se comporta el ofuscador.
    private double priveteField;
    protected double protectedField;
    public double publicField;
    double packageField;
    private double priveteMethod(){
        return 1.0;
    }
    protected double protectedMethod(){
        return 2.0;
    }
    public double publicMethod(){
        return 3.0;}
    double packageMethod(){
        return 4.0;
    }

    private static IfzPublic instance;
    static public IfzPublic getInstance(){
        if (null==instance){
            instance = new PrivateClass();
        }
        return instance;
    }

}
