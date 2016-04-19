package com.chuidiang.examples.osgi_server.vertx;



import com.chuidiang.examples.osgi_interface.Data;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.buffer.Buffer;

public class OsgiSender {
   private EventBus eb;

   public void setEventBus(EventBus eb){
      this.eb = eb;
      eb.consumer("hola",message->{
         System.out.println(message.body());
         Data data = new Data();
         data.value=Math.random();
//         eb.send("data", data);
      });
   }
   
   public void removeEventBus(EventBus eb){
      this.eb=null;
   }
}
