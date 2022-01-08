package com.chuidiang.examples.boot_security.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientMain {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ClientMain.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}
