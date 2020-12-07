package com.chuidiang.examples.spring_jms.client;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fjabellan
 * @date 28/11/2020
 */
@Slf4j
public class JmsTopicReceiver {
    public void receiveMessage(Object theData) {
        log.info("Recibido de topic " + theData);
    }
}
