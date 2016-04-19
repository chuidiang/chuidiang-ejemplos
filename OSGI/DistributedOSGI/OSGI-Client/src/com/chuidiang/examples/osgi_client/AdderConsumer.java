package com.chuidiang.examples.osgi_client;

import java.util.logging.Logger;

import com.chuidiang.examples.osgi_interface.Adder;

public class AdderConsumer {
   private Logger log = Logger.getLogger(AdderConsumer.class.getName());
   private Adder adder;

   public void setAdder(Adder newAdder) {
      log.info("llega adder");
      this.adder = newAdder;
      Thread t = new Thread() {
         public void run() {
            while (null!=adder) {
               try {
                  Thread.sleep(1000);
                  log.info("3+4=" + AdderConsumer.this.adder.add(3, 4));
               } catch (Exception e) {
                  log.warning("Error " + e.getMessage());
               }
            }
         }
      };
      t.start();
   }

   public void removeAdder(Adder adder) {
      log.info("Adder removed");
      this.adder = null;
   }
}
