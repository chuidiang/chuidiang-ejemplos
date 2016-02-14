package com.chuidiang.examples.EJB_Example.threads;

import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ThreadExample {
   private static final Logger LOG = Logger.getLogger(ThreadExample.class.getName());
   @EJB
   TheSlowWork slowWork;
   
   @Schedule(hour="*",minute="*",second="*")
   public void init(){
      slowWork.run();
      Future<Integer> result = slowWork.add(3,4);
      slowWork.waitAndShowResult(result);
   }
}
