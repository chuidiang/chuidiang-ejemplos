package com.chuidiang.examples.osgi_module2;

import com.chuidiang.examples.osgi_module2.interfaz.IfzService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JAVIER on 03/02/2017.
 */
public class Service2 implements IfzService2 {
    private static Logger log = LoggerFactory.getLogger(Service2.class);

    public void start(){
        log.info("Service2 starts");
    }

    public void stop(){
        log.info("Service2 stops");
    }
}
