package com.chuidiang.examples.modules.implementation;

import com.chuidiang.examples.modules.interfaces.IfzModule;

/**
 * @author fjabellan 08/02/2024
 */
public class CoreImplementation implements IfzModule {
    @Override
    public String getString() {
        return "Soy CoreImplementation";
    }
}
