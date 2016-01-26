package com.chuidiang.examples.primefaces;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="userLogin")
@SessionScoped
public class UserLogin implements Serializable {
   private String userName;
   private String password;
   public String getUserName() {
      return userName;
   }
   public void setUserName(String userName) {
      this.userName = userName;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   
   public String validate(){
      if ("juan".equals(userName) && "pedro".equals(password)){
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
         .getExternalContext().getSession(false);
         session.setAttribute("userName", userName);
         ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
         try {
            context.redirect(context.getRequestContextPath() + "view.xhtml");
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         return "view";
      } else {
         return "login";
      }
   }
}
