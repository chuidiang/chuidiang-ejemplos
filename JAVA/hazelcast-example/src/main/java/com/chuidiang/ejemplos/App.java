package com.chuidiang.ejemplos;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, InterruptedException
    {
       System.out.println(Math.sqrt(2));
       Config config = new Config();
       HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
       
       IAtomicLong atomicLong = hazelcastInstance.getAtomicLong("soy productor");
       
       boolean cambiado = atomicLong.compareAndSet(0, 1);
       
       
       IMap<String,String> map = hazelcastInstance.getMap("kk");
       
       map.put("hola", "tu", 10, TimeUnit.SECONDS);
       
       while (true){
          Thread.sleep(1000);
          String dato =map.get("hola");
          if (null!=dato){
             System.out.println(dato);
          }
       }
       
       
//       if (cambiado){
//          produce(hazelcastInstance);
//       } else {
//          consume(hazelcastInstance);
//       }
       
    }

   private static void consume(HazelcastInstance hazelcastInstance) {
      IQueue<String> cola = hazelcastInstance.getQueue("cola");
      while (true){
         try {
            System.out.println(cola.take());
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
            System.out.println(cola.size());
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }       
}
