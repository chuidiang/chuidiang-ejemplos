package com.chuidiang.ejemplos.random;

/**
 * Ejemplos de numeros aleatorios usando Math.random()
 *
 * @author chuidiang
 * @date 02/12/2022
 */
public class MathRandom {
    public static void main(String[] args) {
//        randomBetween0and1();
        randomBetweenMaxAndMin();
    }

    /** Uso simple de Math.random()
     * Numeros aleatorios entre 0.0 y 1.0, excluido el 1.0 */
    private static void randomBetween0and1() {
        for (int i=0;i<10;i++){
            double randomValue = Math.random();
            System.out.println("Random Number "+randomValue);
        }
    }

    /** Numeros aleatorios entre MIN y MAX, excluido MAX */
    private static void randomBetweenMaxAndMin() {
        int numberOfDifferentValues = Constants.MAX - Constants.MIN + 1;
        for (int i=0;i<10;i++){
            int randomValue = (int) Math.floor(Math.random() * numberOfDifferentValues + Constants.MIN);
            System.out.println("Random Number "+randomValue);
        }
    }

}
