package com.chuidiang.examples;



import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Verticle1 extends AbstractVerticle {

   private int counter = 0;
   private long timerId = -1;
   private long requestTimerId=-1;
   private Logger LOG = LoggerFactory.getLogger(Verticle1.class);

   @Override
   public void start() throws Exception {
      LOG.info("SERVER="+System.getenv().get("SERVER"));
      if ("yes".equals(System.getenv().get("SERVER"))) {
         timerId = vertx.setPeriodic(1000, message -> {
            counter++;
            vertx.eventBus().publish("Verticle1", Integer.toString(counter));
         });
         
         vertx.eventBus().consumer("response", message -> {
            String body = (String)message.body();
            LOG.info(body);
         });
         
         requestTimerId = vertx.setPeriodic(10000, message -> {
            vertx.eventBus().publish("request","");
         });
      }
      super.start();
   }

   @Override
   public void stop() throws Exception {
      if (-1!=timerId){
         vertx.cancelTimer(timerId);
      }
      super.stop();
   }
}
