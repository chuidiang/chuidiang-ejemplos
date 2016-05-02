package com.chuidiang.examples.cluster;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Server2Main {
   public static void main(String[] args) throws InterruptedException {
      // Override the configuration of the port
      // when specified as program argument
         System.setProperty("akka.remote.netty.tcp.port", "5558");

        
      // Create an Akka system
      ActorSystem system = ActorSystem.create("ClusterSystem", ConfigFactory.defaultApplication());
      
      // Create an actor that handles cluster domain events
      ActorRef publisher = system
            .actorOf(Props.create(Publisher.class), "publisher");

      while (true){
         publisher.tell("Hello", publisher);
         Thread.sleep(1000);
      }
      
   }
}
