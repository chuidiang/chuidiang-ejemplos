package com.chuidiang.pruebas.postconstruct_scheduled;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author fjabellan 04/06/2023
 */
public class PostConstructScheduledMain {
    public static void main(String[] args) throws InterruptedException {
        BeanFactory factory = new FileSystemXmlApplicationContext("src/main/config/post-construct-scheduled-beans.xml");
//        Thread.sleep(10000);
    }
}
