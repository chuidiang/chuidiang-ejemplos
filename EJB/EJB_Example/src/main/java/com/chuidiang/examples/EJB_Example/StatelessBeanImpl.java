/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chuidiang.examples.EJB_Example;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.chuidiang.examples.ejb.StatelesBean;

/**
 * 
 * @author martin
 */
@Stateless(name="StatelesBean")
public class StatelessBeanImpl implements StatelesBean{

   public String sayHello() {
      System.out.println("Hello");
      return "Hello";
   }
}
