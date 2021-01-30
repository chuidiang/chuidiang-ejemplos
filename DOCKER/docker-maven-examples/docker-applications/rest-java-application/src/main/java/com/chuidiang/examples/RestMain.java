package com.chuidiang.examples;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
public class RestMain {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(RestMain.class, args);
    }
}