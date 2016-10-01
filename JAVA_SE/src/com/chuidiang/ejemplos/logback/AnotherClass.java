package com.chuidiang.ejemplos.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnotherClass {
   private static Logger log = LoggerFactory.getLogger(AnotherClass.class);
   
   public void debug(String msg){
      log.debug(msg);
   }
   public void info(String msg){
      log.info(msg);
   }

}
