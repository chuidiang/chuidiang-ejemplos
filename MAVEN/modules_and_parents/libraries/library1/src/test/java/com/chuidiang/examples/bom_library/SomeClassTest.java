package com.chuidiang.examples.bom_library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author fjabellan
 * @date 02/11/2020
 */
public class SomeClassTest {
    @Test
    @DisplayName("simple test")
    public void test(){
        SomeClass someClass = new SomeClass();
        someClass.setValue(2);
        Assertions.assertEquals(2, someClass.getValue());
    }
}
