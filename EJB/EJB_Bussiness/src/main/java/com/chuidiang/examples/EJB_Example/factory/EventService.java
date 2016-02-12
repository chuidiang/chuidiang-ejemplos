package com.chuidiang.examples.EJB_Example.factory;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EventService {
   private static Logger LOG = Logger.getLogger(EventService.class.getName());
   
   @Inject 
   private String message;
   
   public void startService(){
      LOG.info("Message produced "+message);
   }
}
