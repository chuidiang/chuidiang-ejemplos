package com.chuidiang.ejemplos.random;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by JAVIER on 18/04/2017.
 */
public class RandomString {
    public static void main(String[] args) {
        // One big random number printed in base 32
        SecureRandom random = new SecureRandom();
        System.out.println("Random String " + new BigInteger(130, random).toString(32));


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
}
