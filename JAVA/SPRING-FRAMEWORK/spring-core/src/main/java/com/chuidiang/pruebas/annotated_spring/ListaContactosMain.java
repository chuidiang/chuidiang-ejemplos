package com.chuidiang.pruebas.annotated_spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author fjabellan 03/06/2023
 */
public class ListaContactosMain {
    public static void main(String[] args) {
        BeanFactory factory = new FileSystemXmlApplicationContext("src/main/config/agenda-anotaciones-beans.xml");
        System.out.println(factory.getBean(ListaContactos.class));

        // Si ponemos nombre a ListaPepeContactos, podemos pedirlo por nombre. Ver anotacion
        // comentada en la clase ListaContactos.java
        // System.out.println(factory.getBean("ListaDePepe"));

    }
}
