package com.chuidiang.examples.EJB_Example.factory;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class FactoryMain {
   
   @EJB
   EventService event;
   
   @Schedule(hour="*",minute="*",second="*/5")
   public void produceStrings(){
      event.startService();
   }
}
