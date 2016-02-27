package com.chuidiang.examples.EJB_Example.observer;

import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.chuidiang.examples.ejb.observer.DataObserved;


/**
 * This EJB produces DataObserved instances. 
 * The class AnObserver will receive them.
 * 
 * @author Chuidiang
 */
@Singleton
@Startup
public class FireObserver {
   private static final Logger LOG = Logger.getLogger(FireObserver.class.getName());
   
   @Inject
   Event<DataObserved> event;
   
   private int aNumber=0;
   private String aString="Hello";
   
   @Schedule(hour="*",minute="*",second="*/5")
   public void fireEvent(){
      DataObserved data = new DataObserved();
      aString = aString+" "+aNumber;
      data.setaString(aString);
      data.setaNumber(aNumber++);
      
      event.fire(data);
      LOG.info("event fired!!");
   }
}
