/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chuidiang.examples.EJB_Example;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.chuidiang.examples.ejb.StatelesBean;

/**
 * 
 * @author martin
 */
@Stateless(name="StatelesBean")
public class StatelessBeanImpl implements StatelesBean{
   private static Logger LOG = Logger.getLogger(StatelessBeanImpl.class.toString());
   public String sayHello() {
      LOG.info("Hello from an EJB");
      return " Hello from an EJB !! ";
   }
}
