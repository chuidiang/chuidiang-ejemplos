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

        theStrangeStarter();
    }

    /**
     * Si se instancia Vertx.vertx() sin mas, salta un error java de ServiceProvider
     * diciendo que VertxFactory no es "subtype". Esta forma extraña de arranque,
     * sacada de este ejemplo de vertx
     * https://github.com/vert-x3/vertx-examples/blob/master/osgi-examples/src/main/java/io/vertx/example/osgi/TcclSwitch.java
     * parece funcionar.
     * La alternativa es poner las dependencias de OSGI en el manifiesto como DynamicInport-Package en
     * vez de como Import-Package
     */
    private void theStrangeStarter() {
        ClassLoader original = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(VertxInit.class.getClassLoader());
            vertx = Vertx.vertx();
            eb = vertx.eventBus();
        } finally {
            Thread.currentThread().setContextClassLoader(original);
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
