package com.chuidiang.lombok_example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author fjabellan
 * @date 21/11/2020
 */
public class SomeProcessTest {
    @Test
    public void aTest(){
        SomeProcess process = new SomeProcess();
        process.doSomething();
        Assertions.assertTrue(true);
    }
}
