package com.chuidiang.examples.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fjabellan
 * @date 21/05/2022
 */
@OpenAPIDefinition(info = @Info(
        title = "Open API chuidiang example",
        version = "1.0"
))
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
