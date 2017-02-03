package com.chuidiang.examples.osgi_module2;

import com.chuidiang.examples.osgi_module2.interfaz.IfzIhm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JAVIER on 03/02/2017.
 */
public class Ihm implements IfzIhm {
    private static Logger log = LoggerFactory.getLogger(Ihm.class);

    public void start(){
        log.info("Ihm starts");
    }

    public void stop(){
        log.info("Ihm stops");
    }
}
