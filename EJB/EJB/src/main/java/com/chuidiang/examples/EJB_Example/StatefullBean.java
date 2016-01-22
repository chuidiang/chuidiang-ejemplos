package com.chuidiang.examples.EJB_Example;

import java.io.Serializable;

import javax.ejb.Stateful;


@Stateful
public class StatefullBean implements Serializable{
   
   
   private int value = 0;

   public int getValue() {
      return value;
   }

   public void setValue(int value) {
      this.value = value;
   }
}
