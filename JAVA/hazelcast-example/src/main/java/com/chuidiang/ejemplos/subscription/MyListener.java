package com.chuidiang.ejemplos.subscription;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.MapEvent;
import com.hazelcast.map.impl.MapListenerAdapter;

public class MyListener extends MapListenerAdapter<String, Object> {

   @Override
   public void entryAdded(EntryEvent<String, Object> event) {
      System.out.println("Added: "+event.getKey()+" = "+event.getValue());
   }

   @Override
   public void entryRemoved(EntryEvent<String, Object> event) {
      System.out.println("Removed: "+event.getKey()+" = "+event.getValue());
   }

   @Override
   public void entryUpdated(EntryEvent<String, Object> event) {
      System.out.println("Updated: "+event.getKey()+" = "+event.getValue());
   }

   @Override
   public void mapCleared(MapEvent event) {
      System.out.println("Map Cleared");
   }

}
