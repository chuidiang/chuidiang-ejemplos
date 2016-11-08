package com.chuidiang.ejemplos.subscription;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class SubscriptionMain {
   public static void main(String[] args) {
      HazelcastInstance instance = Hazelcast.newHazelcastInstance();
      IMap<String, String> aMap = instance.getMap("aMap");

      aMap.addEntryListener(new MyListener(), true);

      
      aMap.put("key", "value");
      aMap.put("key", "Another value");
      aMap.remove("key");
      aMap.put("other key", "other value");
      aMap.clear();
      
      instance.shutdown();
   }
}
