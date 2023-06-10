package com.chuidiang.pruebas.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fjabellan 04/06/2023
 */
@Component
public class ScheduledAnnotatedSample {
    @Scheduled(initialDelay = 2000, fixedRate = 1000)
    public void repeat(){
        System.out.println(getClass().toString()+".repeat() llamado");
    }
}
