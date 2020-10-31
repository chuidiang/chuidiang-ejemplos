package com.chuidiang.ejemplos.service_loader;

/**
 * @author fjabellan
 * @date 31/10/2020
 */
public class AService implements IfzService {
    @Override
    public void sayHello(){
        System.out.println("Hello world!");
    }
}
