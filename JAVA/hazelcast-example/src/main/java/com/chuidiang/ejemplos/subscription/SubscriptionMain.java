package com.chuidiang.ejemplos.subscription;

import com.hazelcast.config.Config;
import com.hazelcast.config.GlobalSerializerConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Date;

public class SubscriptionMain {
   public static void main(String[] args){
       Config config = new Config();
       GlobalSerializerConfig globalConfig = new GlobalSerializerConfig();
       globalConfig.setOverrideJavaSerialization(true).setImplementation(new DataSerializer());

       config.getSerializationConfig().setGlobalSerializerConfig(globalConfig);

      HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);

      IMap<String, String> aMap = instance.getMap("aMap");

      aMap.addEntryListener(new MyListener(), true);

      
      aMap.put("key", "value");
      aMap.put("key", "Another value");
      aMap.remove("key");
      aMap.put("other key", "other value");
      aMap.clear();


      IMap<String, Data> otherMap = instance.getMap("otherMap");

      otherMap.addEntryListener(new MyListener(), true);
      otherMap.put("key", new Data());
      Data data = otherMap.get("key");
      data.date=new Date();
      data.value=1000;
      otherMap.put("key",data);

      instance.shutdown();
   }
}
