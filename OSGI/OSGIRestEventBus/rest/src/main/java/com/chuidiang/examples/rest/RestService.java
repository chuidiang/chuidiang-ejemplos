package com.chuidiang.examples.rest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * Created by chuidiang on 29/11/17.
 */
@Component
public class RestService {
    @Activate
    public void start() {
        System.out.println("Arranca bundle RestService");
    }

    @Deactivate
    public void stop() {

    }
}
