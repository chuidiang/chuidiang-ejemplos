package com.chuidiang.examples.session_bean;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * An stateless bean.
 * 
 * @author Chuidiang
 *
 */
@Stateless
@LocalBean
public class StatelessBean  {

   private static Logger LOG = Logger.getLogger(StatelessBean.class.getName());

   public void saySomething(String somethingToSay){
      LOG.info(somethingToSay);
   }
}
