package com.chuidiang.examples.modules;

import com.chuidiang.examples.modules.interfaces.IfzModule;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ServiceLoader<IfzModule> loader = ServiceLoader.load(IfzModule.class);
        loader.iterator().forEachRemaining(module -> System.out.println(module.getString()));
    }
}
