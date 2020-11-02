package com.chuidiang.examples.pitest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author fjabellan
 * @date 02/11/2020
 */
public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp(){
        calculator = new Calculator();
    }
    @Test
    public void simpleTests(){
        double result = calculator.add(2.0,2.0);
        Assert.assertEquals("simple add test", 4.0, result ,1e-6);

        result=calculator.subtract(10.0,5.0);
        Assert.assertEquals("simple bad subtraction test",5.0,result, 1e-6);

        result = calculator.multiply(1.0,2.0);
        Assert.assertEquals("simple bad divide test",2.0 ,result, 1e-6);

        result = calculator.divide(1.0,2.0);
        Assert.assertEquals("simple bad divide test",0.5 ,result, 1e-6);
    }
}
