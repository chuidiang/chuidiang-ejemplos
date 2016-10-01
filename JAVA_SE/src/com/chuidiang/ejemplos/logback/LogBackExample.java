package com.chuidiang.ejemplos.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LogBackExample {

   private static Logger logger = LoggerFactory.getLogger(LogBackExample.class);

   public static void main(String[] args) throws InterruptedException {
      AnotherClass another = new AnotherClass();
      while (true) {
         logger.debug("Hello world.");
         logger.warn("Hello world.!!");
         another.debug("Hello world.");
         another.info("Hello world.!!");

         Thread.sleep(400);
      }

   }

}
