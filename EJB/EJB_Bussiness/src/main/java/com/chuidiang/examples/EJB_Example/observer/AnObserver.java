package com.chuidiang.examples.EJB_Example.observer;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import com.chuidiang.examples.ejb.observer.DataObserved;

@Stateless
public class AnObserver {
   private static Logger LOG = Logger.getLogger(AnObserver.class.getName());

   public void doSomething(@Observes DataObserved data) {
      LOG.info("Arrived "+data);
   }
}
