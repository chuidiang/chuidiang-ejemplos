package com.chuidiang.examples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertySourceExample {

   public static void main(String[] args) {
      // Fix path for file3.properties
      System.setProperty("aPath", "src/main/config");
      
      // Load spring context
      ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
      
      // Get bean and show it's content
      OneBean bean = (OneBean)context.getBean("theBean");
      System.out.println(bean.getaInt());
      System.out.println(bean.getaString());
      System.out.println(bean.isaBool());
   }

}
