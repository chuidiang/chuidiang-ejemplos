package com.chuidiang.ejemplos.misc;

/**
 * @author Chuidiang
 * date 25/02/2024
 * Ejemplos para verificar el tipo de caracter: mayúsculas, minúsculas, dígitos,
 * espacios, etc.
 */
public class UpperLowerExample {
    public static void main(String[] args) {
        // Cadena con mayúsculas, minúsculas, números y caracteres especiales.
        // \u2164 es cinco en números romanos Ⅴ
        String s = "aáA #1çº\u2164\t\n";

        System.out.println(s.toLowerCase());
        System.out.println(s.toUpperCase());

        s.chars().forEach(l -> {
            System.out.printf("%c is LowerCase %b\n", l, Character.isLowerCase(l));
        });

        s.chars().forEach(l -> {
            System.out.printf("%c is UpperCase %b\n", l, Character.isUpperCase(l));
        });

        s.chars().forEach(l -> {
            System.out.printf("%c is Digit %b\n", l, Character.isDigit(l));
        });

        s.chars().forEach(l -> {
            System.out.printf("%c is Alphabetic %b\n", l, Character.isAlphabetic(l));
        });

        s.chars().forEach(l -> {
            System.out.printf("%c is Letter %b\n", l, Character.isLetter(l));
        });

        s.chars().forEach(l -> {
            System.out.printf("%c is Letter or digit %b\n", l, Character.isLetterOrDigit(l));
        });

        s.chars().forEach(l -> {
            System.out.printf("%c is space char %b\n", l, Character.isSpaceChar(l));
        });

        s.chars().forEach(l -> {
            System.out.printf("%c is white space char %b\n", l, Character.isWhitespace(l));
        });
    }
}
