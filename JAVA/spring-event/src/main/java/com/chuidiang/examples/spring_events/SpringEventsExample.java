package com.chuidiang.examples.spring_events;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fjabellan
 * @date 20/12/2021
 */
public class SpringEventsExample {
	public static void main(String[] args) throws InterruptedException {
        new ClassPathXmlApplicationContext("spring-context.xml").start();
	}
}
