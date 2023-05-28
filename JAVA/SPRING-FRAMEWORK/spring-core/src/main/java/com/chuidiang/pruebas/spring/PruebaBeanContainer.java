package com.chuidiang.pruebas.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.cglib.core.Local;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.format.datetime.DateFormatter;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.Locale;

/**
 * @author fjabellan 28/05/2023
 */

public class PruebaBeanContainer {

    public static void main(String[] args) {
        BeanFactory factory = new FileSystemXmlApplicationContext("src/main/config/beans.xml");
        System.out.println(factory.getBean("Juan"));
        System.out.println(factory.getBean("Pedro"));

        BeanFactory factory2 = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(factory2.getBean("Juan"));
        System.out.println(factory2.getBean("Pedro"));
    }
}