package com.chuidiang.examples.spring_jms.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fjabellan
 * @date 28/11/2020
 */
public class JmsServerMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-server-context.xml");
    }

}
