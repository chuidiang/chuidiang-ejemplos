package com.chuidiang.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Chuidiang
 * @date 11/11/2023
 */
public class CalculatorJupiterTest
{
    private Calculator calculator;

    @BeforeEach
    public void init(){
        calculator = new Calculator();
    }

    @Test
    public void shouldAdd()
    {
        Assertions.assertEquals(4, calculator.add(2,2), "Should Add Numbers" );
    }

    @Test
    public void shouldSubstract()
    {
        Assertions.assertEquals(0, calculator.substract(2,2), "Should Substract Numbers" );
    }

    @Test
    public void shouldMultiply()
    {
        Assertions.assertEquals(4, calculator.multiply(2,2), "Should Multiply Numbers" );
    }

    @Test
    public void shouldDivide()
    {
        Assertions.assertEquals(1, calculator.divide(2,2), "Should Divide Numbers" );
    }

}
