package com.chuidiang.ejemplos.random;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Ejemplo de numeros aleatorios usando un stream
 * Created by Chuidiang on 18/04/2017.
 */
public class Java8RandomExample {

    public static void main(String[] args) {
        Random random = new Random();

        IntStream intStream = random.ints(Constants.STREAM_SIZE,
                Constants.MIN,
                Constants.MAX +1);

        /* Recorrer con un bucle forEach() */
        intStream.forEach(value ->
                System.out.println("Random Number "+value)
        );
    }
}
