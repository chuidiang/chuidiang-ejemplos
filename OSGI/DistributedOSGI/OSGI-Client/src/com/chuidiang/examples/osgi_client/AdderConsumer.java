package com.chuidiang.examples.osgi_client;

import java.util.logging.Logger;

import com.chuidiang.examples.osgi_interface.Adder;

public class AdderConsumer {
   private Logger log = Logger.getLogger(AdderConsumer.class.getName());
   private Adder adder;
   
   public void setAdder(Adder adder){
      this.adder=adder;
      log.info("3+4="+this.adder.add(3, 4));
   }
   
   public void removeAdder(Adder adder){
      log.info("Adder removed");
      this.adder=null;
   }
}
