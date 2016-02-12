package com.chuidiang.examples.EJB_Example.factory;

import javax.enterprise.inject.Produces;

public class EventProducer {
   @Produces
   public String getMessage() {
      return "Hello!!"+Math.random();
   }
}
