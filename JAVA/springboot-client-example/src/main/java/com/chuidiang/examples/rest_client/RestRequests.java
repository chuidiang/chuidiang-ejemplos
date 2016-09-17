package com.chuidiang.examples.rest_client;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestRequests {
   public void getOneGreeting(RestTemplate restTemplate) {
      // We want Greeting at index=0
      Greeting aGreeting = restTemplate
            .getForObject("http://localhost:8080/greeting/0", Greeting.class);
      System.out.println("Get=0 -> " + aGreeting);

   }

   public void deleteGreeting(RestTemplate restTemplate) {
      // We want to delete Greeting at index=2
      restTemplate.delete("http://localhost:8080/greeting/2");

   }

   public void putGreeting(RestTemplate restTemplate) {
      Greeting aGreeting = new Greeting(1, "Modified Greeting");

      // We want to modify the Greeting at index=1
      restTemplate.put("http://localhost:8080/greeting/1", aGreeting);
   }

   public void postGreeting(RestTemplate restTemplate) {
      String name = "New Greeting";
      MultiValueMap<String, Object> values = new LinkedMultiValueMap<String, Object>();
      values.add("content", name);
      Greeting gretting = restTemplate.postForObject(
            "http://localhost:8080/greeting", values, Greeting.class);
      System.out.println("POSTED: " + gretting);
   }

   public void getAndShowGreetings(RestTemplate restTemplate) {
      Greeting[] greetings = restTemplate
            .getForObject("http://localhost:8080/greeting", Greeting[].class);

      for (Greeting greeting : greetings) {
         System.out.println(greeting);
      }
      System.out.println("----------------");
   }
}
