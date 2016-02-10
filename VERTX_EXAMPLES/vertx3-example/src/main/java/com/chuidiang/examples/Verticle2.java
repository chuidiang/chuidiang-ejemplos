package com.chuidiang.examples;

import java.util.logging.Logger;

import io.vertx.core.AbstractVerticle;

public class Verticle2 extends AbstractVerticle {

   private Logger LOG = Logger.getLogger(Verticle1.class.getName());

   @Override
   public void start() throws Exception {
      vertx.eventBus().consumer("Verticle1", message -> {
         LOG.info("Message arrived : " + message.body());
      });
      super.start();
   }

   @Override
   public void stop() throws Exception {
      // TODO Auto-generated method stub
      super.stop();
   }
}
