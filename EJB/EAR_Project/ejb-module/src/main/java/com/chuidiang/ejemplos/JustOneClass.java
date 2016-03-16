package com.chuidiang.ejemplos;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.commons.codec.binary.Base64;

@Singleton
@Startup
public class JustOneClass {
   private static Logger LOG = Logger.getLogger(JustOneClass.class.toString());
   
   @PostConstruct
   public void start(){
      LOG.info("Singleton started!!");
      
      Base64 b64 = new Base64();
      LOG.info("Base 64 = "+b64.encodeAsString("Hola".getBytes()));
   }
}
