package com.chuidiang.ejemplos.random;

import java.util.*;

/**
 * Created by JAVIER on 18/04/2017.
 */
public class NotRepeatedRandomNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(40);
        for (int i=1;i<41;i++){
            numbers.add(i);
        }

        Random random = new Random();

        while (numbers.size()>1){
            int randomIndex = random.nextInt(numbers.size());
            System.out.println("Not Repeated Random Number "+numbers.get(randomIndex));
            numbers.remove(randomIndex);
        }

        System.out.println("-----------------");

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
