package com.chuidiang.examples;

import akka.actor.UntypedActor;

public class HelloActor extends UntypedActor {

   @Override
   public void onReceive(Object arg0) throws Exception {
      System.out.println(arg0);
   }

}
