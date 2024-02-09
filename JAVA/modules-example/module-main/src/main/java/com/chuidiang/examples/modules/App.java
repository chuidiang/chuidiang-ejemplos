package com.chuidiang.examples.modules;

import com.chuidiang.examples.modules.interfaces.IfzModule;

import java.util.ServiceLoader;

/**
 * fjabellan 09/02/2024
 * Ejemplo java 9 modules. Obtencion de los servicios implementados
 */
public class App 
{
    public static void main( String[] args )
    {
        ServiceLoader<IfzModule> loader = ServiceLoader.load(IfzModule.class);
        loader.iterator().forEachRemaining(module -> System.out.println(module.getString()));
    }
}
