package com.chuidiang.examples;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       // an actor needs an ActorSystem
       ActorSystem system = ActorSystem.create("HelloWorldSystem");

       // create and start the actor
       Props actor = Props.create(HelloActor.class);
       ActorRef helloActor = system.actorOf(actor, "HelloActor");

       // send the actor two messages
       helloActor.tell("hello 1",helloActor);
       helloActor.tell("hello 2",helloActor);
       helloActor.tell("hello 3",helloActor);
      

       // shut down the system
       system.terminate();
    }
}
