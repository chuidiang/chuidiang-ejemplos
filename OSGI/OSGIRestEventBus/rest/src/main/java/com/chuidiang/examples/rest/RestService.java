package com.chuidiang.examples.rest;

import com.chuidiang.examples.interfaces.EventBusIfz;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import java.text.MessageFormat;

/**
 * Created by chuidiang on 29/11/17.
 */
@Component
public class RestService {
    int count=0;
    public RestService(){
        System.out.println("A mi tambien me instancian");
    }
    private EventBusIfz eb;

    @Activate
    public void start() {
        System.out.println("Arranca bundle RestService");
    }

    @Deactivate
    public void stop() {

    }

    @Reference
    public void addEventBus(EventBusIfz eb){
        System.out.println("Me llega Event Bus");
        this.eb = eb;

        eb.consumer("prueba", (topic,message)-> {
            System.out.println(MessageFormat.format("Recibido message {0} en topic {1}",message,topic));
        });
        eb.publish("prueba", "hello !!!!!");

        eb.setPeriodic(1000, id -> {
           eb.publish("prueba", count++);
        });
    }
}
