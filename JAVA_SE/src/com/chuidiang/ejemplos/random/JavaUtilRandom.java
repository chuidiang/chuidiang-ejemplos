package com.chuidiang.ejemplos.random;

import java.util.Random;

/**
 * Ejemplos de numeros aleatorios usando java.util.Random
 * @author Chuidiang
 * @date 02/12/2022
 */
public class JavaUtilRandom {
    public static void main(String[] args) {
        basicRandom();
        seedRandom();
        rangeRandom();
    }

    private static void rangeRandom() {
        Random random = new Random();
        for (int i=0;i<10;i++){
            /* MAX - MIN + 1 es 6-1+1, es decir 6
               nextInt() devuelve entre 0 incluido y 6 excluido */
            int randomValue = random.nextInt(Constants.MAX -Constants.MIN +1);

            /* sumamos MIN para obtener entre 1 y 6 */
            randomValue = randomValue+Constants.MIN;

            System.out.println("Random Value "+randomValue);
        }
    }

    private static void seedRandom() {
        long seed = 23456;
        for (int sequence=0; sequence<3; sequence++) {
            Random random = new Random(seed);
            for (int i = 0; i < 5; i++) {
                int randomValue = random.nextInt();
                System.out.println("Random Value "+randomValue);
            }
            System.out.println("-------------");
        }
    }

    private static void basicRandom() {
        Random random = new Random();
        for (int i=0;i<10;i++){
            int randomValue = random.nextInt();
            System.out.println("Random Value "+randomValue);
        }
    }
}
