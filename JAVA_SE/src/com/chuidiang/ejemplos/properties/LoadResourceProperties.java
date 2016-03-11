package com.chuidiang.ejemplos.properties;

import java.io.FileWriter;
import java.io.InputStream;
import java.util.Properties;

public class LoadResourceProperties {
   public static void main(String[] args) {
      
         Properties p = new Properties();
         try {
            
            InputStream propertiesStream = ClassLoader.getSystemResourceAsStream("properties/config.properties");
            p.load(propertiesStream);
            propertiesStream.close();
            
            System.out.println("uno="+p.getProperty("uno"));
            
            p.setProperty("cuatro", "4");
            p.store(new FileWriter("target/out.properties"),"Some comment");
         } catch (Exception e){
            System.err.println("Error reading file "+e.getMessage());
         }

      

   }
}
