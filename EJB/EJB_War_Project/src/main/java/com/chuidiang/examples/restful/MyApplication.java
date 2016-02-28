package com.chuidiang.examples.restful;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class MyApplication extends Application{
   @Override
   public Set<Class<?>> getClasses() {
      Set<Class<?>> classes = new HashSet<>();
      classes.add(RestfulService.class);
      return classes;
   }
}
