package com.chuidiang.examples;

import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.IntStream;

public class Java8RandomExample {
    public static void main(String[] args) {
        Random random = new Random();

        // IntStream for values between 1 and 6 both included
        final IntStream ints = random.ints(1, 7);

        // Iterator to get integers
        final PrimitiveIterator.OfInt iterator = ints.iterator();

        // Loop to print 10 integers.
        int counter=0;
        while (iterator.hasNext() && counter<10){
            System.out.println(iterator.next());
            counter++;
        }

        // Close de stream
        ints.close();
    }
}
