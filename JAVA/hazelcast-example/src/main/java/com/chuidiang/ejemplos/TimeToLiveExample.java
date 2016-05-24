package com.chuidiang.ejemplos;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * Hazelcast Time to Live example.
 * A Hazelcast map where elements expires after a time. 
 */
public class TimeToLiveExample 
{
    public static void main( String[] args ) throws FileNotFoundException, InterruptedException
    {
       Config config = new Config();
       HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
       
       IMap<String,String> map = hazelcastInstance.getMap("TimeToLiveMap");
       
       map.put("hola", "tu", 10, TimeUnit.SECONDS);
       
       while (true){
          Thread.sleep(1000);
          String dato =map.get("hola");
          if (null!=dato){
             System.out.println(dato);
          } else {
             break;
          }
       }
       System.out.println("Data expired");
    }
}
