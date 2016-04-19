package com.chuidiang.examples.osgi_client.vertx;

import io.vertx.core.eventbus.EventBus;

public class VertxConsumer {
   EventBus eb;
   
   public void setEventBus(EventBus eb){
      this.eb = eb;
      eb.consumer("hola",message -> {
         System.out.println(message.body());
      });
   }
   
   public void removeEventBus(EventBus eb){
      this.eb=null;
   }
}
