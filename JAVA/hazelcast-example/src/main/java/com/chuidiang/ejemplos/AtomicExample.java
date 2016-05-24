package com.chuidiang.ejemplos;

import java.io.FileNotFoundException;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;
import com.hazelcast.core.IQueue;

/**
 * Hazelcast atomic integer example.
 * If several instances of this program are launched, only the first one become producer.
 * The other instances will be consumers.
 */
public class AtomicExample 
{
    public static void main( String[] args ) throws FileNotFoundException, InterruptedException
    {
       Config config = new Config();
       HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
       
       IAtomicLong atomicLong = hazelcastInstance.getAtomicLong("soy productor");
       
       boolean cambiado = atomicLong.compareAndSet(0, 1);
       
       if (cambiado){
          produce(hazelcastInstance);
       } else {
          consume(hazelcastInstance);
       }
       
    }

   private static void consume(HazelcastInstance hazelcastInstance) {
      IQueue<String> cola = hazelcastInstance.getQueue("cola");
      while (true){
         try {
            System.out.println("Taken from queue: "+cola.take());
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      
   }

   private static void produce(HazelcastInstance hazelcastInstance) {
      IQueue<String> cola = hazelcastInstance.getQueue("cola");
      
      int count=0;
      while (true){
         try {
            cola.offer(Integer.toString(count++));
            Thread.sleep(1000);
            System.out.println("Added to queue. It has now "+cola.size());
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }       
}
