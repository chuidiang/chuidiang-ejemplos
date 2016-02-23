package com.chuidiang.examples.session_bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class SingletonBean {
   @PostConstruct
   public void init() {
      System.out.println("SingletonBean started");
   }
   
   @PreDestroy
   public void destroy() {
      System.out.println("SingletonBean is going to be destroyed");
   }
}
