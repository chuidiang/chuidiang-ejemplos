package com.chuidiang.language;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class TestGetProbabilities {

    ProbabilitiesCalculator calculator;

    @Before
    public void init(){
        calculator = new ProbabilitiesCalculator();
    }

    @Test
    public void getSimpleLetterCount(){
        String input = "abc";
        calculator.addLine(input);
        Map<String, Map<Character,Integer>> probabilities = calculator.getProbabilities();

        Assert.assertEquals(1, probabilities.size());
        Assert.assertNotNull(probabilities.get("ab"));
        Assert.assertEquals(1, probabilities.get("ab").get('c').intValue());
    }

    @Test
    public void getSeveralLetterCount(){
        String input = "abcabc abcabc";
        calculator.addLine(input);
        Map<String, Map<Character,Integer>> probabilities = calculator.getProbabilities();

        Assert.assertEquals(5, probabilities.size());
        Assert.assertEquals(4, probabilities.get("ab").get('c').intValue());
        Assert.assertEquals(2, probabilities.get("bc").get('a').intValue());
        Assert.assertEquals(2, probabilities.get("ca").get('b').intValue());
        Assert.assertNull(probabilities.get("ca").get(' '));
    }

    @Test
    public void addTwoLines(){
        String input = "abcabc";
        calculator.addLine(input);
        calculator.addLine(input);
        Map<String, Map<Character,Integer>> probabilities = calculator.getProbabilities();

        Assert.assertEquals(5, probabilities.size());
        Assert.assertEquals(4, probabilities.get("ab").get('c').intValue());
        Assert.assertEquals(2, probabilities.get("bc").get('a').intValue());
        Assert.assertEquals(2, probabilities.get("ca").get('b').intValue());
        Assert.assertNull(probabilities.get("ca").get(' '));

    }

}
