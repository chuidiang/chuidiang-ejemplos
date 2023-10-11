package com.chuidiang.language;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class TextGenerator {
    public static void main(String[] args) {
        TextGenerator textGenerator = new TextGenerator();
        textGenerator.generate("./src/spanish-sample.txt");
    }

    private void generate(String sampleFile) {
        Map<String, int[]> probabilities = getProbabilities(sampleFile);
    }

    private Map<String,int[]> getProbabilities(String sampleFile) {

        try {
            Files.lines(Paths.get(sampleFile)).forEach(line -> {

            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
