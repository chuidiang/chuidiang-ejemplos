package com.chuidiang.examples.cluster;

import com.chuidiang.examples.HelloActor;
import com.chuidiang.examples.HelloActorMain;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.ClusterDomainEvent;

public class Server2Main {
   public static void main(String[] args) throws InterruptedException {
      // Override the configuration of the port
      // when specified as program argument
         System.setProperty("akka.remote.netty.tcp.port", "5558");

        
      // Create an Akka system
      ActorSystem system = ActorSystem.create("ClusterSystem", ConfigFactory.defaultApplication());
      
      // Create an actor that handles cluster domain events
      ActorRef clusterListener = system
            .actorOf(Props.create(HelloActor.class), "clusterListener");

      // Add subscription of cluster events
      Cluster.get(system).subscribe(clusterListener, ClusterDomainEvent.class);
      
      
      while (true){
         clusterListener.tell("Hello", clusterListener);
         Thread.sleep(1000);
      }
      
   }
}
