package com.chuidiang.examples.observer;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

/**
 * An EJB observer.
 * This EJB observes ObservedData data.
 * 
 * @author Chuidiang
 *
 */
@Stateless
public class AnObserver {
   private static Logger LOG = Logger.getLogger(AnObserver.class.getName());

   public void doSomething(@Observes ObservedData data) {
      LOG.info("Arrived "+data);
   }
}
