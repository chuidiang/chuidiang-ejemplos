package com.chuidiang.examples.EJB_Example.observer;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.chuidiang.examples.ejb.observer.DataObserved;

@Singleton
@Startup
public class FireObserver {
   
   @Inject
   Event<DataObserved> event;
   
   private int aNumber=0;
   private String aString="Hello";
   
   public void fireEvent(){
      DataObserved data = new DataObserved();
      aString = aString+" "+aNumber;
      data.setaString(aString);
      data.setaNumber(aNumber++);
      event.fire(data);
   }
}
