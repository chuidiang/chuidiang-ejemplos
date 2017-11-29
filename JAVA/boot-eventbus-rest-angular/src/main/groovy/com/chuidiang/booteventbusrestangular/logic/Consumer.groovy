package com.chuidiang.booteventbusrestangular.logic

import com.chuidiang.booteventbusrestangular.bus.EventBusInit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * Created by chuidiang on 29/11/17.
 */
@Component
class Consumer {
    @Autowired
    EventBusInit eb;

    @PostConstruct
    init() {
        eb.eb.consumer("prueba", { data ->
            println("Recibido " + data.body())
        })
    }
}
