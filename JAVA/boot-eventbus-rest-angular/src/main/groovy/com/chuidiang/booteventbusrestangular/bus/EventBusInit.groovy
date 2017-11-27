package com.chuidiang.booteventbusrestangular.bus

import com.chuidiang.booteventbusrestangular.rest.RandomNumber
import io.vertx.core.Vertx
import io.vertx.core.eventbus.EventBus
import io.vertx.core.json.Json
import io.vertx.ext.bridge.BridgeOptions
import io.vertx.ext.bridge.PermittedOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * Created by chuidiang on 25/11/17.
 */
@Component
class EventBusInit {
    @Autowired
    VertxHttpServer server;

    private EventBus eb;

    @PostConstruct
    void init() {
        Vertx vertx = Vertx.vertx();
        eb = vertx.eventBus();
        vertx.setPeriodic(1000, { id ->
                RandomNumber data = new RandomNumber(value: Math.random())
                String dataAsJson = Json.encode(data)
                eb.publish ("prueba",dataAsJson)
            }
        )

        eb.consumer("prueba", {data ->
                println ("Recibido "+data.body())
            }
        )

        vertx.deployVerticle(server)
    }
}
