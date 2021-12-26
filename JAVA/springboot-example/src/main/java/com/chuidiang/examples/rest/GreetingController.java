package com.chuidiang.examples.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class GreetingController {

   @Autowired
   private Data data;

   @RequestMapping(method = RequestMethod.GET, path = "/greeting")
   public List<Greeting> greeting() {
      return data.getGreetings();
   }

   @RequestMapping(method = RequestMethod.GET, path = "/greeting/{id}")
   public Greeting greeting(@PathVariable Integer id) {

      try {

         Greeting greeting = data.getGreeting(id);
         return greeting;

      } catch (IndexOutOfBoundsException e) {
          throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
      }

   }

   @RequestMapping(method = RequestMethod.DELETE, path = "/greeting/{id}")
   public void delete(@PathVariable Integer id) {
      try {
         data.removeGreeting(id);
      } catch (IndexOutOfBoundsException e) {
         throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
      }
   }

   @RequestMapping(method = RequestMethod.POST, path = "/greeting")
   public Greeting add(@RequestParam(value = "content") String content) {
      return data.addGreeting(content);
   }

   @RequestMapping(method = RequestMethod.PUT, path = "/greeting/{id}")
   public Greeting add(@PathVariable Integer id,
         @RequestBody Greeting greeting) {
      try {
         return data.updateGreeting(id, greeting);
      } catch (IndexOutOfBoundsException e) {
         throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
      }
   }

}