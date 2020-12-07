package com.chuidiang.examples.spring_jms.client;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fjabellan
 * @date 05/12/2020
 */
@Slf4j
public class JmsQueueReceiver {
    public void receiveMessage(Object theData) {
        log.info("Recibido de queue " + theData);
    }
}
