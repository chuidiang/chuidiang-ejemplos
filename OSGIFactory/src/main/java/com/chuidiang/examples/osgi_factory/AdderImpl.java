package com.chuidiang.examples.osgi_factory;

import java.util.Map;

import com.chuidiang.examples.osgi_factory.interfaces.IfzAdder;

public class AdderImpl implements IfzAdder{
   
   private int increment=0;
   private String name=null;
   public void activate(Map<String, String> configuration){
      name = configuration.get("name");
      increment = Integer.parseInt(configuration.get("increment"));
   }

   @Override
   public int add(int a) {
      return a+increment;
   }

   @Override
   public String getName() {
      return name;
   }
}
