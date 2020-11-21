package com.chuidiang.lombok_example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author fjabellan
 * @date 21/11/2020
 */
public class DataTest {

    private Data data;

    @BeforeEach
    public void init(){
        data = new Data();
        data.setAnInteger(10);
        data.setAnString("10");
    }

    @Test
    public void setterAndGetterTest(){
        Assertions.assertEquals(10,data.getAnInteger());
        Assertions.assertEquals("10",data.getAnString());

    }

    @Test
    public void toStringTest(){
        String output = data.toString();
        Assertions.assertEquals("Data(anInteger=10, anString=10)",output);
    }

    @Test
    public void equalsTest(){
        Data data2 = new Data();
        data2.setAnInteger(10);
        data2.setAnString("10");

        Assertions.assertEquals(data,data2);

        data2.setAnInteger(11);
        Assertions.assertNotEquals(data,data2);
    }

    @Test
    public void allArgsConstructorTest(){
        Data data2 = new Data(10,"10");
        Assertions.assertEquals(10,data2.getAnInteger());
        Assertions.assertEquals("10",data2.getAnString());
    }
}
