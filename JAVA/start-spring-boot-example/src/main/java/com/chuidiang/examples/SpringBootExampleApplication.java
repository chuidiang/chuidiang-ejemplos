package com.chuidiang.examples;

import com.chuidiang.examples.init.Initializer;
import com.chuidiang.examples.bean.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(PersonRepository personRepository) {
		return strings -> {
			new Initializer(personRepository);
		};
	}
}
