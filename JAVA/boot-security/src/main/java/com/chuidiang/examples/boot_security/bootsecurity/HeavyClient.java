package com.chuidiang.examples.boot_security.bootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HeavyClient {
    public static void main(String[] args) {
        SpringApplication.run(BootSecurityApplication.class, args);
    }
}
