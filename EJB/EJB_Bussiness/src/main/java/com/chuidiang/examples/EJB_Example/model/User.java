package com.chuidiang.examples.EJB_Example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
   @Id
   @GeneratedValue
   private long userId;
   
   private String name;
   
   private String password;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
   
   public long getUserId() {
      return userId;
   }
}
