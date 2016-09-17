package com.chuidiang.examples.rest_client;

import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;

public class RestClientWithSpringBoot implements CommandLineRunner {
   public static void main(String args[]) {
      SpringApplication.run(RestClientWithSpringBoot.class);
   }

   @Override
   public void run(String... args) throws Exception {
      RestTemplate restTemplate = new RestTemplate();
      RestRequests restRequests = new RestRequests();

      restRequests.getAndShowGreetings(restTemplate);

      restRequests.putGreeting(restTemplate);
      restRequests.getAndShowGreetings(restTemplate);

      restRequests.postGreeting(restTemplate);
      restRequests.getAndShowGreetings(restTemplate);

      restRequests.deleteGreeting(restTemplate);
      restRequests.getAndShowGreetings(restTemplate);

      restRequests.getOneGreeting(restTemplate);
   }

}
