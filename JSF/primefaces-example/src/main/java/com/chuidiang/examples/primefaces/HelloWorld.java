package com.chuidiang.examples.primefaces;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "helloWorld", eager = true)
@SessionScoped
public class HelloWorld {
   private static final Logger LOG = Logger.getLogger(HelloWorld.class.getName());
   private String message="Hello World!";
   
   public HelloWorld() {
      LOG.info("ManagedBean HelloWorld started");
   }
   public String getMessage() {
      return this.message;
   }
   public void setMessage(String message){
      LOG.info("Received "+message);
      this.message=message;
   }
}