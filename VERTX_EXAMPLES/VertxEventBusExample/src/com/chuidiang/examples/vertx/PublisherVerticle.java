package com.chuidiang.examples.vertx;

import org.vertx.java.platform.Verticle;

/**
 * It publish a message once per second.
 * @author Chuidiang
 *
 */
public class PublisherVerticle extends Verticle {
   @Override
   public void start() {
      System.out.println("Publisher started");

      new Thread(new Runnable() {

         @Override
         public void run() {
            int counter = 0;
            while (true) {
               vertx.eventBus().publish("message","Message number "+counter);
               System.out.println("sent " + counter++);
               try {
                  Thread.sleep(1000);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      }).start();
   }
}
