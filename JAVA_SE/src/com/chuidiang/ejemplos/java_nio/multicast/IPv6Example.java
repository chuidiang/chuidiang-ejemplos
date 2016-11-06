package com.chuidiang.ejemplos.java_nio.multicast;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPv6Example {

   private static final Logger LOGGER = LoggerFactory
         .getLogger(IPv6Example.class);

   private static final int PORT = 9876;
   private static final String MCAST_ADDR = "FF12:11::3563";

   private static InetAddress GROUP;

   public static void main(String[] args) {
      try {
         GROUP = InetAddress.getByName(MCAST_ADDR);
         Thread server = server();
         server.start();
         Thread.sleep(3000);
         Thread client = client();
         client.start();
         client.join();
      } catch (Exception e) {
         LOGGER.error("Error ", e);
      }
   }

   private static Thread client() {
      return new Thread(new ClientRunnable(PORT, GROUP));
   }

   private static Thread server() {
      return new Thread(new ServerRunnable(PORT, GROUP));
   }
}
