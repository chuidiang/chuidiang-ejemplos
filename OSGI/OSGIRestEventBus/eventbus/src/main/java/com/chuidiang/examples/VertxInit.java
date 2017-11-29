package com.chuidiang.examples;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * Created by chuidiang on 29/11/17.
 */
@Component
public class VertxInit {
    @Activate
    public void start() {
        System.out.println("Arranca bundle VertxInit");
    }

    @Deactivate
    public void stop() {

    }
}
