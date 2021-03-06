package com.chuidiang.examples.rest_client;

import java.util.logging.Logger;

import org.springframework.web.client.RestTemplate;

public class RestClientWhitoutSpringBoot  {

   public static void main(String args[]) {
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
