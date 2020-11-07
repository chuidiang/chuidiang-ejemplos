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
        double result;

        result=calculator.subtract(10.0,5.0);
        Assert.assertEquals("simple bad subtraction test",5.0,result, 1e-6);

        result = calculator.multiply(1.0,2.0);
        Assert.assertEquals("simple bad divide test",2.0 ,result, 1e-6);

//        result = calculator.divide(1.0,2.0);
//        Assert.assertEquals("simple bad divide test",0.5 ,result, 1e-6);
    }

    @Test
    public void noValidTest(){
        // pitest cambiara dentro del código la suma por una resta, el test seguirá pasando
        // Este test no es muy valido, hay muchas operaciones que dan cero cuando se les
        // pasa dos ceros como parametro: suma, resta y multiplicación.
        double result = calculator.add(0.0,0.0);
        Assert.assertEquals("simple add test", 0.0, result ,1e-6);
    }
}
