package com.chuidiang.examples.spring_jms.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fjabellan
 * @date 28/11/2020
 */
public class JmsGuiMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-gui-context.xml");
    }
}
