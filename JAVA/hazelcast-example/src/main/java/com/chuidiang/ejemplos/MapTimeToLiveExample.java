package com.chuidiang.ejemplos;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.FileNotFoundException;


/**
 * Hazelcast Time to Live example.
 * A Hazelcast map where elements expires after a time. 
 */
public class MapTimeToLiveExample
{
    public static void main( String[] args ) throws InterruptedException
    {
       Config config = new Config();
       HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

       hazelcastInstance.getConfig().addMapConfig(new MapConfig("TimeToLiveMap").setTimeToLiveSeconds(1));
       IMap<String,String> map = hazelcastInstance.getMap("TimeToLiveMap");
       
       map.put("hola", "tu");
       System.out.println("Data added");
       
       while (true){
          Thread.sleep(500);
          String dato =map.get("hola");
          if (null!=dato){
             System.out.println(dato);
          } else {
             break;
          }
       }
       System.out.println("Data expired");
       hazelcastInstance.shutdown();
    }
}
