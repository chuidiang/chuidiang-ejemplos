package com.chuidiang.ejemplos.random;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Ejemplo de numeros aleatorios usando un stream
 * Created by Chuidiang on 18/04/2017.
 */
public class Java8RandomExample {

    public static void main(String[] args) {
        Random random = new Random();

        /* 10 numeros entre MIN incluido y MAX+1 excluido. */
        IntStream intStream = random.ints(10, Constants.MIN, Constants.MAX +1);

        final Iterator<Integer> iterator = intStream.iterator();

        while (iterator.hasNext()){
            System.out.println("Random Number "+iterator.next());
        }
    }
}
