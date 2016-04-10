package com.chuidiang.examples.osgi_factory;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.chuidiang.examples.osgi_factory.interfaces.IfzAdder;

public class AdderConsumer {
   private static final Logger LOG = Logger.getLogger(AdderConsumer.class.getName());
   private List<IfzAdder> adders = new LinkedList<>();
   private boolean stop=false;
   
   public void addAdder(IfzAdder anAdder){
      LOG.info("Adder added");
      adders.add(anAdder);
   }
   
   public void init() {
      LOG.info("AdderConsumer started");
      new Thread(){
         public void run(){
            while(!stop){
               try {
                  Thread.sleep(1000);
                  for (IfzAdder adder:adders){
                     LOG.info(adder.getName() + ": 3+incrment= "+adder.add(3)  );
                  }
               } catch (InterruptedException e) {
                  LOG.warning("Sleep interrupted "+e.getMessage());
               }
            }
         }
      }.start();
      
   }
   
   public void stop() {
      stop=true;
   }
   
   public void removeAdder(IfzAdder anAdder){
      LOG.info("adder removed");
      adders.remove(anAdder);
   }
}
