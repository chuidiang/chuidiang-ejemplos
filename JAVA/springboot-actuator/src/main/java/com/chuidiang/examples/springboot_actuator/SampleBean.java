package com.chuidiang.examples.springboot_actuator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chuidiang
 * date 16/12/2023
 */
@Configuration
public class SampleBean {
    @Bean
    public SampleBean theBean(){
        return new SampleBean();
    }
}
