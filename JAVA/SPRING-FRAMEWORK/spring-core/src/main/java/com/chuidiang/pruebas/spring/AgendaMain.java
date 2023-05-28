package com.chuidiang.pruebas.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author fjabellan 28/05/2023
 */

public class AgendaMain {

    public static void main(String[] args) {
        BeanFactory factory = new FileSystemXmlApplicationContext("src/main/config/agenda-beans.xml");
        System.out.println(factory.getBean("agenda"));
    }
}