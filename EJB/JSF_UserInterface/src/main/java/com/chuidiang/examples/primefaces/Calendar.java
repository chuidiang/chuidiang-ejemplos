package com.chuidiang.examples.primefaces;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="calendar")
@SessionScoped
public class Calendar {
   private Date date=new Date();

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }
   
}
