package com.chuidiang.examples;

import io.vertx.core.AbstractVerticle;

public class Verticle1 extends AbstractVerticle {

   private int counter = 0;
   private long timerId = -1;

   @Override
   public void start() throws Exception {
      timerId = vertx.setPeriodic(1000, message -> {
         counter++;
         vertx.eventBus().publish("Verticle1", Integer.toString(counter));
      });
      super.start();
   }

   @Override
   public void stop() throws Exception {
      vertx.cancelTimer(timerId);
      super.stop();
   }
}
