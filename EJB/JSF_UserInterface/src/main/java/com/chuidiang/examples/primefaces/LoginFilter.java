package com.chuidiang.examples.primefaces;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter{

   private static final Logger LOG = Logger.getLogger(LoginFilter.class.toString());
   @Override
   public void destroy() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void doFilter(ServletRequest req, ServletResponse res,
         FilterChain chain) throws IOException, ServletException {
      
      HttpServletRequest httpReq = (HttpServletRequest)req;
      HttpServletResponse httpRes = (HttpServletResponse)res;
      
      String relativePath = ((HttpServletRequest)req).getServletPath(); // + ((HttpServletRequest)req).getPathInfo();
      if ("/login.xhtml".equals(relativePath)){
         chain.doFilter(req, res);
      } else {
         HttpSession session = httpReq.getSession();
         if (null!=session.getAttribute("userName")){
            chain.doFilter(req, res);
         } else {
            ((HttpServletResponse)res).sendRedirect(((HttpServletRequest)req).getContextPath() + "/login.xhtml");
         }
      }
      
   }

   @Override
   public void init(FilterConfig arg0) throws ServletException {
      // TODO Auto-generated method stub
      
   }

}
