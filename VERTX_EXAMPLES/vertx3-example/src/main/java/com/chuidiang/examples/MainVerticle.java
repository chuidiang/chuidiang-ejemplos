package com.chuidiang.examples;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {

   AbstractVerticle verticles[] = { new Verticle1(), new Verticle2(),
         new WebServerVerticle() };
   String id[] = new String[verticles.length];
   int counter = 0;

   @Override
   public void start() throws Exception {
      for (int i = 0; i < verticles.length; i++) {
         vertx.deployVerticle(verticles[i], event -> {
            if (event.succeeded()) {
               id[counter++] = event.result();
            }
         });
      }
   }

   @Override
   public void stop() throws Exception {
      for (String verticleId : id) {
         if (null != verticleId) {
            vertx.undeploy(verticleId);
         }
      }
   }
}
