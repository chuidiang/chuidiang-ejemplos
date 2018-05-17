package com.chuidiang.examples.springinjection;

import com.chuidiang.examples.springinjection.ifz.ModuleIfz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @Autowired
    List<ModuleIfz> modules = new ArrayList<>();

    @PostConstruct
    public void start(){
        modules.forEach((module) ->
                    module.someMethod()
        );
    }
}
