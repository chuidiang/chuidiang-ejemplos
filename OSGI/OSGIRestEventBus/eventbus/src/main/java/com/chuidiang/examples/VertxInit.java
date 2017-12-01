package com.chuidiang.examples;


import com.chuidiang.examples.interfaces.ConsumerIfz;
import com.chuidiang.examples.interfaces.EventBusIfz;
import com.chuidiang.examples.interfaces.PeriodicConsumerIfz;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.text.MessageFormat;

/**
 * Created by chuidiang on 29/11/17.
 */
@Component
public class VertxInit implements EventBusIfz  {
    private EventBus eb;
    private Vertx vertx;

    public VertxInit(){
        System.out.println("Al menos me instancian");
    }


    @Activate
    public void start() {
        System.out.println("Arranca bundle VertxInit");
        try {
            vertx = Vertx.vertx();
            eb = vertx.eventBus();
        } catch (Throwable e){
            e.printStackTrace();
        }
    }

    @Deactivate
    public void stop() {

    }

    @Override
    public void publish(String topic, Object data) {
        System.out.println(MessageFormat.format("Publicado en {0} el dato {1}",topic, data));
        eb.publish(topic, data);
    }

    @Override
    public void consumer(String topic, ConsumerIfz consumer){
        eb.consumer(topic, message -> {
            consumer.processMessage(message.address(), message.body());
        });
    }

    public long setPeriodic (long timer, PeriodicConsumerIfz consumer){
            return vertx.setPeriodic(timer, id -> {consumer.handle(id);
        });
    }
}
