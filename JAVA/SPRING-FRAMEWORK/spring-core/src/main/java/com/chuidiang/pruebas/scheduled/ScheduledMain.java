package com.chuidiang.pruebas.scheduled;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author fjabellan 04/06/2023
 */
public class ScheduledMain {
    public static void main(String[] args) throws InterruptedException {
        BeanFactory factory = new FileSystemXmlApplicationContext("src/main/config/scheduled-beans.xml");
    }
}
