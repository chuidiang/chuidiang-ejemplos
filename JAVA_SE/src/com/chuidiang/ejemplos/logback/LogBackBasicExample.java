package com.chuidiang.ejemplos.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackBasicExample {

   private static Logger logger = LoggerFactory.getLogger(LogBackBasicExample.class);

   public static void main(String[] args) throws InterruptedException {
         logger.debug("Hello world.");
         logger.warn("Hello world.!!");
   }

}
