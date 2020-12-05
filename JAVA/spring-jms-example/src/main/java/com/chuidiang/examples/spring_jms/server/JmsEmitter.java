package com.chuidiang.examples.spring_jms.server;

import com.chuidiang.examples.spring_jms.common.Constants;
import com.chuidiang.examples.spring_jms.common.Data;
import com.chuidiang.examples.spring_jms.common.SonData;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;


/**
 *
 * @author fjabellan
 * @date 28/11/2020
 */
@Slf4j
public class JmsEmitter {
    private int counter=0;
    @Autowired
    @Qualifier("jmsTopicTemplate")
    JmsTemplate topicTemplate;

    @Autowired
    @Qualifier("jmsQueueTemplate")
    JmsTemplate queueTemplate;

    /** Tras arrancar todo se llama a este metodo para que empiece a enviar por JMS */
    @PostConstruct
    public void init(){

        new Thread(new Runnable() {
            @SneakyThrows(InterruptedException.class)
            @Override
            public void run() {

                while (true) {
                    sendData();
                    Thread.sleep(1000);
                }

            }
        }).start();


    }

    private void sendData() {
        log.info("Envio "+counter);
        topicTemplate.convertAndSend(Constants.TOPIC, new Data(counter,"The Text "+counter));

        SonData sonData= new SonData(counter,counter,"The Text"+counter);
        sonData.setString("Son Data"+counter);
        sonData.setTheDouble(counter);
        sonData.setValue(counter);
        queueTemplate.convertAndSend(Constants.QUEUE,sonData);

        counter++;
    }
}
