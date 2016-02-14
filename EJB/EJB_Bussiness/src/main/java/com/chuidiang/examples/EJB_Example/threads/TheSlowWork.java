package com.chuidiang.examples.EJB_Example.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import org.jboss.vfs.spi.AssemblyFileSystem;

@Stateless
public class TheSlowWork {
   private static Logger LOG = Logger.getLogger(TheSlowWork.class.getName());
   @Asynchronous
   public void run(){
      LOG.info("Go!");
      try {
         Thread.sleep(Math.round(Math.random()*1500));
      } catch (InterruptedException e) {
         LOG.log(Level.INFO,"Error!",e);
      }
      LOG.info("Done!");
   }
   
   @Asynchronous
   public Future<Integer> add(int a, int b){
      LOG.info("Adding!");
      
      try {
         Thread.sleep(Math.round(Math.random()*1500));
      } catch (InterruptedException e) {
         
      }
      LOG.info("Added!");
      return new AsyncResult<Integer>(a+b);
   }

   @Asynchronous
   public void waitAndShowResult(Future<Integer> value){
      try {
         LOG.info("Result="+value.get());
      } catch (InterruptedException | ExecutionException e) {
         LOG.log(Level.INFO,"Error!",e);
      }
   }
}
