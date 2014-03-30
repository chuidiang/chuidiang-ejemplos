package com.chuidiang.examples.vertx;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.platform.PlatformLocator;
import org.vertx.java.platform.PlatformManager;
import org.vertx.java.platform.Verticle;

/**
 * Main Verticle. It deploys PublisherVerticle and SubscriberVerticle.
 * There is a main method to start vertx from java. Also, we can start vertx from 
 * command line in the project directory.
 * @author Chuidiang
 *
 */
public class MainVerticle extends Verticle {

   /** It starts vertx and deploys this project module */
   public static void main(String[] args) throws InterruptedException, IOException {

      PlatformManager pm = PlatformLocator.factory.createPlatformManager(5558,"localhost");
      
      pm.deployModule("com.chuidiang.examples~vertx~1.0.0", null, 1, new Handler<AsyncResult<String>>() {
         
         @Override
         public void handle(AsyncResult<String> arg0) {
            System.out.println("module deploying result "+arg0.result());
         }
      });
   }
   
   /** It deploys PublisherVerticle and SubscriberVerticle */
   @Override
   public void start() {
      container.deployVerticle(PublisherVerticle.class.getCanonicalName());
      container.deployVerticle(SubscriberVerticle.class.getCanonicalName(),3);
   }

   /**
    * Not used.
    * This method is usefull to deploy Verticles/Workers instead of modules. deployVerticle()
    * and deployWorker() has a URL[] classpath parameter we can obtain with this method.
    * @return
    */
   public static URL[] getClassPathAsURLArray() {
      
      String classPath = System.getProperty("java.class.path");
      
      String[] splitClassPath = classPath.split(";");
      
      URL[] classPathAsURLArray = new URL[splitClassPath.length];
      
      for (int i=0; i<splitClassPath.length; i++) {
          
          try {
              classPathAsURLArray[i] = new URL("file:///" + splitClassPath[i].replace('\\', '/'));
          } catch (MalformedURLException ex) {
              classPathAsURLArray = null;
          }
      
      }
      
      return classPathAsURLArray;
  }
}
