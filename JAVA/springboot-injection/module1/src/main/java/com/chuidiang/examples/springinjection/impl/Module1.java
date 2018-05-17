package com.chuidiang.examples.springinjection.impl;

import com.chuidiang.examples.springinjection.ifz.ModuleIfz;
import org.springframework.stereotype.Component;

@Component
public class Module1 implements ModuleIfz{
    public Module1 (){
        System.out.println("Module1 started");
    }
    @Override
    public void someMethod() {
        System.out.println("I'm module 1");
    }
}
