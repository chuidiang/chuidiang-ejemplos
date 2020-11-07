package com.chuidiang.examples.bom_library;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fjabellan
 * @date 02/11/2020
 */
@Slf4j @Data
public class SomeClass {
    private int value;

    public void doSomething(){
        log.info("I'm doing something");
    }
}
