package com.chuidiang.examples;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Chuidiang
 * @date 11/11/2023
 */
public class CalculatorVintageTest
{
    private Calculator calculator;

    @Before
    public void init(){
        calculator = new Calculator();
    }

    @Test
    public void shouldAdd()
    {
        Assert.assertEquals("Should Add Numbers", 4, calculator.add(2,2) );
    }

    @Test
    public void shouldSubstract()
    {
        Assert.assertEquals("Should Substract Numbers", 0, calculator.substract(2,2) );
    }

    @Test
    public void shouldMultiply()
    {
        Assert.assertEquals("Should Multiply Numbers", 4, calculator.multiply(2,2) );
    }

    @Test
    public void shouldDivide()
    {
        Assert.assertEquals("Should Divide Numbers", 1, calculator.divide(2,2) );
    }

}
