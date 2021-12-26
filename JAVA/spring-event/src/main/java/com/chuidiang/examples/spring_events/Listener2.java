package com.chuidiang.examples.spring_events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author fjabellan
 * @date 20/12/2021
 */
@Component
public class Listener2 {
	@EventListener
	public void onMyEvent(MyEvent event)  {
		System.out.println("Listener 2 "+event.text);

	}
}
