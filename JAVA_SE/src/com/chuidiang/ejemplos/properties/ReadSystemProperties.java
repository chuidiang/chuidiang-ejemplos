package com.chuidiang.ejemplos.properties;

import java.util.Map;
import java.util.Set;

public class ReadSystemProperties {

   public static void main(String[] args) {
     String user = System.getProperty("user");
     String password = System.getProperty("password");
     
     System.out.println("user = "+user);
     System.out.println("password = "+password);

     String myVar = System.getenv().get("MI_VARIABLE");
     System.out.println("MI_VARIABLE="+myVar);
     
     Map<String,String> env = System.getenv();
     Set<String> keys = env.keySet();
     for (String key: keys){
        System.out.println(key + " = "+env.get(key));
     }
   }

}
