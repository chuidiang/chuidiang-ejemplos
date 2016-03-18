package com.chuidiang.examples.rest;

import java.util.List;

import javax.sound.sampled.DataLine;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@RestController
public class GreetingController {

   @Autowired
   private Data data;

   @RequestMapping(method = RequestMethod.GET, path = "/greeting")
   public List<Greeting> greeting() {
      return data.getGreetings();
   }

   @RequestMapping(method = RequestMethod.GET, path = "/greeting/{id}")
   public Greeting greeting(@PathVariable Integer id)
         throws NoSuchRequestHandlingMethodException {

      try {

         Greeting greeting = data.getGreeting(id);
         return greeting;

      } catch (IndexOutOfBoundsException e) {
         throw new NoSuchRequestHandlingMethodException("greeting",
               GreetingController.class);
      }

   }

   @RequestMapping(method = RequestMethod.DELETE, path = "/greeting/{id}")
   public void delete(@PathVariable Integer id)
         throws NoSuchRequestHandlingMethodException {
      try {
         data.removeGreeting(id);
      } catch (IndexOutOfBoundsException e) {
         throw new NoSuchRequestHandlingMethodException("greeting",
               GreetingController.class);
      }
   }

   @RequestMapping(method = RequestMethod.POST, path = "/greeting")
   public Greeting add(@PathParam(value = "content") String content) {
      return data.addGreeting(content);
   }

   @RequestMapping(method = RequestMethod.PUT, path = "/greeting/{id}", consumes="application/json")
   public Greeting add(@PathVariable Integer id,
         @RequestBody Greeting greeting)
               throws NoSuchRequestHandlingMethodException {
      try {
         return data.updateGreeting(id, greeting);
      } catch (IndexOutOfBoundsException e) {
         throw new NoSuchRequestHandlingMethodException("greeting",
               GreetingController.class);
      }
   }

}