package com.chuidiang.examples.vertx;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

/**
 * It subscribes to event bus and prints messages received.
 * @author Chuidiang
 *
 */
public class SubscriberVerticle extends Verticle {
   private static int id = 0;
   private int myId = id++;

   @Override
   public void start() {
      System.out.println("consumer started " + myId);
      vertx.eventBus().registerHandler("message", new Handler<Message>() {

         @Override
         public void handle(Message arg0) {
            System.out.println("" + arg0.body() + " received by " + myId);

         }
      });
   }
}
