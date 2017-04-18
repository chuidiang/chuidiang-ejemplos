package com.chuidiang.ejemplos.random;

import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by JAVIER on 18/04/2017.
 */
public class Java8RandomExample {
    public static void main(String[] args) {
        Random random = new Random();

        IntStream intStream = random.ints(10, 1, 7);
        final PrimitiveIterator.OfInt iterator = intStream.iterator();

        while (iterator.hasNext()){
            System.out.println("Random Number "+iterator.next());
        }

        intStream = random.ints(10, 1, 7);
        intStream.forEach(value ->
                System.out.println("Random Number "+value)
        );
    }
}
