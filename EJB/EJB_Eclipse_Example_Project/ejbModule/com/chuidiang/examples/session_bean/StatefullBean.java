package com.chuidiang.examples.session_bean;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * An Statefull bean.
 * 
 * @author Chuidiang
 *
 */
@Stateful
@LocalBean
public class StatefullBean {
   
   @EJB
   StatelessBean statelessTalker;
   
   // The state of this Statefull bean.
   private String someText;
   
   // Set the state
   public void setText(String someText){
      this.someText = someText;
   }
   
   // It uses de state and release de Statefull bean.
   @Remove
   public void sayText(){
      statelessTalker.saySomething(someText);
   }
}
