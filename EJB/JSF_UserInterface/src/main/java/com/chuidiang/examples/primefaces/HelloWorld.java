package com.chuidiang.examples.primefaces;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.chuidiang.examples.ejb.StatelesBean;

@ManagedBean(name = "helloWorld", eager = true)
@SessionScoped
public class HelloWorld {
   private static final Logger LOG = Logger.getLogger(HelloWorld.class.getName());
   private String message="Hello World!";
   
   @EJB(lookup="java:global/EJB_Bussiness/StatelesBean")
   private StatelesBean statelesBean;
   
   public String getMessage() {
      return message + statelesBean.sayHello();
   }
   public void setMessage(String message){
      LOG.info("Received "+message);
      this.message=message;
   }
}