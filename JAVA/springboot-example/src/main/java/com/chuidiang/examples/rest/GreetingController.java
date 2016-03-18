package com.chuidiang.examples.rest;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@RestController
public class GreetingController {

   private static final String template = "Hello, %s!";
   private final AtomicLong counter = new AtomicLong();
   private Data data = new Data();

   @RequestMapping(method=RequestMethod.GET, path="/greeting")
   public List<Greeting> greeting(){
         return data.getGreetings();
   }
   
   @RequestMapping(method=RequestMethod.GET, path="/greeting/{id}")
   public Greeting greeting(@PathVariable Integer id) throws NoSuchRequestHandlingMethodException{
         Greeting greeting= data.getGreeting(id);
         if (null==greeting){
            throw new NoSuchRequestHandlingMethodException("greeting",GreetingController.class); 
         }
         return greeting;
   }

}