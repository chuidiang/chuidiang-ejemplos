package com.chuidiang.pruebas.postconstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author fjabellan 04/06/2023
 */
public class PostConstructMain {
    public static void main(String[] args) throws InterruptedException {
        BeanFactory factory = new FileSystemXmlApplicationContext("src/main/config/post-construct-beans.xml");
    }
}
