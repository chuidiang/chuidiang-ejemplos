package com.chuidiang.examples.spring_events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author fjabellan
 * @date 20/12/2021
 */
@Component
public class Listener1 {
	@EventListener
	public void onEvent(MyEvent event){
		System.out.println("Listener 1 " + event.text);
	}
}
