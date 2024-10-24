package com.chuidiang.examples.sse_back_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SseBackDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SseBackDemoApplication.class, args);
	}

}
