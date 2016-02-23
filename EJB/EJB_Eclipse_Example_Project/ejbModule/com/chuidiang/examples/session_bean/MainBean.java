package com.chuidiang.examples.session_bean;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 * Singleton Bean example.
 * 
 * This bean hss two methods. 
 * The first one is called every 10 seconds from second 0 of every minute.
 * The second is called every 10 secons from secon 5 of every minute.
 * 
 * The first method gets a Statefull bean and set its state.
 * The second method uses de Staefull bean and release it.
 * 
 * @author Chuidiang
 */
@Singleton
@LocalBean
public class MainBean {
   private static Logger LOG = Logger.getLogger(MainBean.class.getName());

   @Resource
   EJBContext context;

   StatefullBean statefullTalker = null;

   private int counter = 0;

   @Schedule(second = "0/10", minute = "*", hour = "*")
   public void fixText() {
      
      // Get a instance of the Statefull bean.
      if (null == statefullTalker) {
         statefullTalker = (StatefullBean) context
               .lookup("java:module/StatefullBean");
      }
      
      // Set the state of the Statefull bean.
      String text = "Hello " + counter++;
      LOG.info("Fix text " + text);
      statefullTalker.setText(text);
   }

   @Schedule(second = "5/10", minute = "*", hour = "*")
   public void say() {
      
      // Uses the Statefull bean and release it.
      if (null != statefullTalker) {
         statefullTalker.sayText();
         statefullTalker = null;
      }
   }
}
