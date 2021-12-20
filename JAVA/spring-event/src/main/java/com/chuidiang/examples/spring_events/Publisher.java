package com.chuidiang.examples.spring_events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fjabellan
 * @date 20/12/2021
 */
@Component
@EnableScheduling
public class Publisher {
	@Autowired
	ApplicationEventPublisher publisher;

	@Scheduled(fixedRate = 1000)
	public void init() {
		publisher.publishEvent(new MyEvent("texto del evento"));
		System.out.println("ya");
	}
}
