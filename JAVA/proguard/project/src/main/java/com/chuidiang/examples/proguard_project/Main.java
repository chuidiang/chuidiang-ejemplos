package com.chuidiang.examples.proguard_project;

import com.chuidiang.examples.proguard_library.shared.IfzPublic;
import com.chuidiang.examples.proguard_library.shared.PublicClass;

/**
 * @author fjabellan
 * @date 01/11/2020
 */
public class Main {
    public static void main(String[] args) {
        IfzPublic service =PublicClass.getInstance();
        System.out.println(service.add(1.2, 3.5));
    }
}
