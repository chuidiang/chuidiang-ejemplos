package com.chuidiang.ejemplos.properties;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.Properties;



public class LoadFileProperties {

   public static void main(String[] args) {
      Properties p = new Properties();
      try {
         p.load(new FileReader("files/properties/config.properties"));
         System.out.println("uno="+p.getProperty("uno"));
         System.out.println("cuatro="+p.getProperty("cuatro", "default value"));
         
         Enumeration<Object> keys = p.keys();
         while (keys.hasMoreElements()){
            Object key = keys.nextElement();
            System.out.println(key + " = "+ p.get(key));
         }
         
         p.setProperty("cuatro", "4");
         p.store(new FileWriter("target/out.properties"),"Some comment");
      } catch (Exception e){
         System.err.println("Error reading file "+e.getMessage());
      }

   }

}
