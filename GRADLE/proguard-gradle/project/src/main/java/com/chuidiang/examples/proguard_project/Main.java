package com.chuidiang.examples.proguard_project;

import com.chuidiang.examples.proguard_library.shared.IfzPublic;
import com.chuidiang.examples.proguard_library.shared.PublicClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fjabellan
 * @date 01/11/2020
 */
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        IfzPublic service =PublicClass.getInstance();
        log.info("la suma es {}",service.add(1.2, 3.5));
    }
}
