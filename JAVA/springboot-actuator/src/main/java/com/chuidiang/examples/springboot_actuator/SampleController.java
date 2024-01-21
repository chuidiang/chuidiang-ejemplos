package com.chuidiang.examples.springboot_actuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chuidiang
 * date 16/12/2023
 */
@RestController
public class SampleController {
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }
}
