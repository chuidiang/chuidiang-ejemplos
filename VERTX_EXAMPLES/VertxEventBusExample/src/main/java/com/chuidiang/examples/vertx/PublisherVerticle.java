package com.chuidiang.examples.vertx;

import org.vertx.java.core.Handler;
import org.vertx.java.platform.Verticle;

/**
 * It publish a message once per second.
 * @author Chuidiang
 *
 */
public class PublisherVerticle extends Verticle {
   private int counter=0;
   
   @Override
   public void start() {
      System.out.println("Publisher started");
      
      long timerId = vertx.setPeriodic(1000, new Handler<Long>() {
         @Override
         public void handle(Long arg0) {
            vertx.eventBus().publish("message","Message number "+counter);
            System.out.println("sent " + counter++);
         }
      });
   }
}
