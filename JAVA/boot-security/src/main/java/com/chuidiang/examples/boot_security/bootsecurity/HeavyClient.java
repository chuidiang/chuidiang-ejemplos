package com.chuidiang.examples.boot_security.bootsecurity;

import com.chuidiang.examples.boot_security.bootsecurity.BootSecurityApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class HeavyClient {
    public static void main(String[] args) {
        SpringApplication.run(BootSecurityApplication.class, args);
    }
}
