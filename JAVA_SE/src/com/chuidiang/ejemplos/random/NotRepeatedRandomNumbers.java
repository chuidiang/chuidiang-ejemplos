package com.chuidiang.ejemplos.random;

import java.util.*;

/**
 * Created by Chuidiang on 18/04/2017.
 */
public class NotRepeatedRandomNumbers {
    public static void main(String[] args) {
        choose10RandomElements();
        generate10NonRepeatedRandomNumbers();
    }

    /** Con una lista de 40 elementos, numeros en este caso, elige aleatoriamente 10 */
    private static void choose10RandomElements() {
        /* Lista con 40 numeros, de 1 a 40. Representan cartas de baraja española */
        List<Integer> numbers = new ArrayList<>(40);
        for (int i=1;i<41;i++){
            numbers.add(i);
        }

        Random random = new Random();

        for (int i=0;i<10;i++){
            int randomIndex = random.nextInt(numbers.size());
            System.out.println("Not Repeated Random Number "+numbers.get(randomIndex));
            numbers.remove(randomIndex);
        }
    }

    /** Genera 10 numeros aleatorios no repetidos entre 0 y 39 */
    private static void generate10NonRepeatedRandomNumbers() {
        Random random = new Random();

        Set<Integer> alreadyUsedNumbers = new HashSet<>();
        while (alreadyUsedNumbers.size()<10) {
            int randomNumber = random.nextInt(40);
            if (!alreadyUsedNumbers.contains(randomNumber)){
                System.out.println("Not Repeated Random Number "+randomNumber);
                alreadyUsedNumbers.add(randomNumber);
            }
        }

    }
}
