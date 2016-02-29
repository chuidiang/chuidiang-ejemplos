package com.chuidiang.examples.restful;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * This class is needed to registry Web Service classes.
 * It only needs to extends Application and overwrite de getClasses() method.
 * 
 * Alternatively, instead of this class, we can configure web services via web.xml file
 * 
 * The annotation @ApplicationPath("resources") fix the initial path for the web services,
 * in this example, http://<host>:<port>/<application name>/resources/
 * 
 * @author Chuidiang
 *
 */
@ApplicationPath("resources")
public class MyApplication extends Application{
   
   @Override
   public Set<Class<?>> getClasses() {
      Set<Class<?>> classes = new HashSet<>();
      classes.add(RestfulService.class);
      return classes;
   }
}
