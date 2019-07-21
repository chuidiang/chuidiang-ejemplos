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
        restTemplate= restTemplateBuilder.basicAuthentication("borrador", "borrador").build();
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);

                    Double value = restTemplate.getForObject("http://localhost:8080/mi/double/borrar", Double.class);
                    System.out.println("borrar = " +value);
                } catch (Exception e) {
                    System.out.println("No puedo borrar "+e.getMessage());
                }

                try {
                    Double value = restTemplate.getForObject("http://localhost:8080/mi/double/crear", Double.class);
                    System.out.println("crear = "+value);
                } catch (Exception e){
                    System.out.println("No puedo crear "+e.getMessage());
                }

            }
        }.start();

    }
}
