package com.chuidiang.booteventbusrestangular.bus

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.eventbus.EventBus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * Created by chuidiang on 25/11/17.
 */
@Component
class EventBusInit {
    @Autowired
    List<AbstractVerticle> verticles;

    EventBus eb;
    Vertx vertx;

    @PostConstruct
    void init() {
        vertx = Vertx.vertx();
        eb = vertx.eventBus();
        verticles.each({verticle ->
            vertx.deployVerticle(verticle)
            println "Verticle Deployed"
        })

    }
}
