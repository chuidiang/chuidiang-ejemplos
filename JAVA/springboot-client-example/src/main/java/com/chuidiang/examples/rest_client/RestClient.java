package com.chuidiang.examples.rest_client;

import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;

public class RestClient implements CommandLineRunner {
   private static final Logger log = Logger.getLogger(RestClient.class.toString());

   public static void main(String args[]) {
       SpringApplication.run(RestClient.class);
   }

   
   @Override
   public void run(String... args) throws Exception {
       RestTemplate restTemplate = new RestTemplate();
       Greeting[] greetings = restTemplate.getForObject(
             "http://localhost:8080/greeting", Greeting[].class);
              
       for (Greeting greeting:greetings){
          System.out.println(greeting);
       }
   }
}
