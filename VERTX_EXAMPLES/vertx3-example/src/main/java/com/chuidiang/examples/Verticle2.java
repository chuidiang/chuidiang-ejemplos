package com.chuidiang.examples;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Verticle2 extends AbstractVerticle {

   private Logger LOG = LoggerFactory.getLogger(Verticle2.class);
   
   Statistics s = new Statistics();

   @Override
   public void start() throws Exception {
      vertx.eventBus().consumer("Verticle1", message -> {
         LOG.info("Message arrived : " + message.body());
         s.counter++;
      });
      
      vertx.eventBus().consumer("request", message-> {
         vertx.eventBus().send("response", Json.encode(s));
      });
      super.start();
   }

   @Override
   public void stop() throws Exception {
      // TODO Auto-generated method stub
      super.stop();
   }
}
