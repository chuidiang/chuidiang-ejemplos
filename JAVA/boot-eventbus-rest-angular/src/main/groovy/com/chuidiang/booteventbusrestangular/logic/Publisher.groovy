package com.chuidiang.booteventbusrestangular.logic

import com.chuidiang.booteventbusrestangular.bus.VertxInit
import io.vertx.core.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * Created by chuidiang on 29/11/17.
 */
@Component
class Publisher {
    @Autowired
    VertxInit eb;

    @PostConstruct
    init(){
        eb.vertx.setPeriodic(1000, {id ->
            eb.eb.publish("prueba", Json.encode(new RandomNumber(value:Math.random())))
        })
    }
}
