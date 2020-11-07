package com.chuidiang.examples.bom_subproject;

import com.chuidiang.examples.bom_library.SomeClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fjabellan
 * @date 02/11/2020
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        SomeClass someClass = new SomeClass();
        someClass.setValue(11);
        log.info(someClass.toString());
    }
}
