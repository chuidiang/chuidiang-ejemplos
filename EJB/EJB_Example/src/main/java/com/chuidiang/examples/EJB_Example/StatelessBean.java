/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chuidiang.examples.EJB_Example;

import javax.ejb.Stateless;

/**
 * 
 * @author martin
 */
@Stateless
public class StatelessBean {

   public String sayHello() {
      System.out.println("Hello");
      return "Hello";
   }
}
