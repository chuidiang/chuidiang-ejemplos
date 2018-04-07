package com.chuidiang.language;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProbabilitiesCalculator {

    private Map<String,Map<Character,Integer>> probabilities;

    public void addLine(String input) {
        if (null==probabilities){
            probabilities = new HashMap<>();
        } else {
            input = " "+input;
        }

        char [] line = input.toCharArray();

        for (int i=0;i<line.length-2;i++){
            String key = new String(new char[]{line[i],line[i+1]});
            if (null==probabilities.get(key)){
                probabilities.put(key, new HashMap<>());
            }

            Map<Character,Integer> charProbability = probabilities.get(key);

            if (null==charProbability.get(line[i+2])){
                charProbability.put(line[i+2],1);
            } else {
                charProbability.put(line[i+2],charProbability.get(line[i+2])+1);
            }
        }
    }

    public Map<String,Map<Character,Integer>> getProbabilities() {
        return probabilities;
    }
}
