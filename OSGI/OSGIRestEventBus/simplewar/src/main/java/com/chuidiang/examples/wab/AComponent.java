package com.chuidiang.examples.wab;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.logging.Logger;

/**
 * Created by chuidiang on 7/12/17.
 */
@Component
public class AComponent {
    Logger logger = Logger.getLogger(getClass().getName());
    @Activate
    public void start(){
        logger.info("logger wab activado");
    }

    @Deactivate
    public void stop() {
        logger.info("wab desactivado");
    }

    public static void main(String[] args) {
        new AComponent().start();
    }
}
