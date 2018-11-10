package com.chuidiang.examples.boot_security.bootsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class Client {
    public Client() {
        System.out.println("cliente lanzado");
    }

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    RestTemplate restTemplate;

    @PostConstruct
    public void useWebService (){
        restTemplate= restTemplateBuilder.basicAuthentication("user", "user").build();
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);

                    Double value = restTemplate.getForObject("http://localhost:8080/borrar", Double.class);
                    System.out.println(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
}
