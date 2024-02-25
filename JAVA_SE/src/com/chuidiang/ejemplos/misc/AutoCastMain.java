package com.chuidiang.ejemplos.misc;

/**
 * @author Chuidiang
 * date 25/02/2024
 * Para probar el cast automático en switch y con instanceof
 */
public class AutoCastMain {
    public static void main(String[] args) {
        Object value = "Una Cadena";

        // instanceof permite cast automático
        if (value instanceof String s){
            System.out.println(s.toLowerCase());
        }

        // También switch permite ahora
        switch (value) {
            case Integer anInteger :
                System.out.println("Es entero "+anInteger);
                break;
            case String aString:
                System.out.println("Es string "+aString);
                break;
            case Double aDouble :
                System.out.println("Es double "+aDouble);
                break;
            default:
                System.out.println("Ninguno de los anteriores");
        }
    }
}
