package com.chuidiang.mockito_examples;

import java.io.FilterOutputStream;

/**
 * Clase para sacar resultados por pantalla
 *
 * @author chuidiang
 * @date 15/11/2020
 */
public class OutputClass {
    public void printOutput(String theString){
        System.out.println(theString);
    }
    public void printPrettyOutput(String theString) {
        System.out.println("I'm the pretty String : "+theString);
    }
}
