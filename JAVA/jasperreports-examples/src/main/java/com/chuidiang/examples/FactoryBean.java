package com.chuidiang.examples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author fjabellan 31/10/2023
 */
public class FactoryBean {
    public static Collection<Bean> create(){
        List<Bean> beans = new ArrayList<>();
        for (int i=0;i<10;i++) {
            beans.add(new Bean("Nombre"+i, new Date(),"nombre"+i+"@gmail.com"));
        }
        return beans;
    }
}
