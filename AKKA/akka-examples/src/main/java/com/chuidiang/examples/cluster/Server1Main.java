package com.chuidiang.examples.cluster;

import com.chuidiang.examples.HelloActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.ClusterDomainEvent;

public class Server1Main {
   public static void main(String[] args) throws InterruptedException {
      // Override the configuration of the port
      // when specified as program argument
         System.setProperty("akka.remote.netty.tcp.port", "5557");
      // Create an Akka system
      ActorSystem system = ActorSystem.create("ClusterSystem");

      // Create an actor that handles cluster domain events
      ActorRef clusterListener = system
            .actorOf(Props.create(HelloActor.class), "clusterListener");

      // Add subscription of cluster events
      Cluster.get(system).subscribe(clusterListener, ClusterDomainEvent.class);
      
   }
}
