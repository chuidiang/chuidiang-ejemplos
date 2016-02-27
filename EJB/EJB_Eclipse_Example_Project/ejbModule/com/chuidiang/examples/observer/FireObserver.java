package com.chuidiang.examples.observer;

import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.inject.Inject;


/**
 * This EJB produces ObservedData instances. 
 * The class AnObserver will receive them.
 * 
 * @author Chuidiang
 */
@Singleton
public class FireObserver {
   private static final Logger LOG = Logger.getLogger(FireObserver.class.getName());
   
   @Inject
   Event<ObservedData> event;
   
   private int aNumber=0;
   
   @Schedule(hour="*",minute="*",second="*/5")
   public void fireEvent(){
      
      ObservedData data = new ObservedData();
      data.setaString("Hello "+aNumber);
      data.setaNumber(aNumber++);
      
      event.fire(data);
      LOG.info("event fired!!");
   }
}
