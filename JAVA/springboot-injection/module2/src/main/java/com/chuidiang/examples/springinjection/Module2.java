package com.chuidiang.examples.springinjection;

import com.chuidiang.examples.springinjection.ifz.ModuleIfz;
import org.springframework.stereotype.Component;

@Component
public class Module2 implements ModuleIfz{
    public Module2(){
        System.out.println("Module2 started");
    }
    @Override
    public void someMethod() {
        System.out.println("I'm module 2");
    }
}
