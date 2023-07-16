package com.chuidiang.ejemplos.random;

import java.math.BigInteger;
import java.util.Random;

/**
 * Genera string aleatorios.
 *
 * Created by Chuidiang on 18/04/2017.
 */
public class RandomString {
    public static void main(String[] args) {
        randomStringWithBigInteger();
//        randomStringFromCharacterList();
    }

    private static void randomStringFromCharacterList() {
        // Choose random chars from an array.
        char [] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        int charsLength = chars.length;

        Random r = new Random();

        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<10;i++){
            buffer.append(chars[r.nextInt(charsLength)]);
        }
        System.out.println("Random String " + buffer.toString());
    }

    private static void randomStringWithBigInteger() {
        // One big random number printed in base 36
        Random random = new Random();
        System.out.println("Random String " + new BigInteger(128, random).toString(32));
    }
}
