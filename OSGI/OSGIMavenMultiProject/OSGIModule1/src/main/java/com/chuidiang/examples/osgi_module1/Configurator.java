package com.chuidiang.examples.osgi_module1;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by JAVIER on 04/02/2017.
 */
public class Configurator implements ManagedService {
    private static Logger log = LoggerFactory.getLogger(Configurator.class);

    @Override
    public void updated(Dictionary<String, ?> dictionary) throws ConfigurationException {
        if (null==dictionary){
            log.warn("Dictionary is null");
            return;
        }

        Enumeration<String> keys = dictionary.keys();
        while (keys.hasMoreElements()){
            String key=keys.nextElement();
            log.info("update: "+key + " = "+dictionary.get(key));
        }
    }

    public void start(Map<String,?> properties){
        log.info("Configurator starts ");
        for (Object key:properties.keySet()){
            log.info("start: key="+key);
            log.info("start: value="+properties.get(key));
        }
    }

    public void stop(){
        log.info("Configurator stop");
    }
}
