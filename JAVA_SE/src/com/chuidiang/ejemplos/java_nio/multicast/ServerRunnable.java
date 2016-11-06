package com.chuidiang.ejemplos.java_nio.multicast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerRunnable implements Runnable {
   private static final int DELAY_BETWEEN_MESSAGES = 1000; // milliseconds
   private int port;
   private InetAddress group;
   private static final Logger LOGGER = LoggerFactory
         .getLogger(ServerRunnable.class);

   public ServerRunnable(int port, InetAddress group) {
      this.port = port;
      this.group = group;
   }

   @Override
   public void run() {
      DatagramSocket serverSocket = null;
      try {
         serverSocket = new DatagramSocket();
         try {
            while (true) {
               String data = "Hello";
               byte[] sendData = data.getBytes();
               DatagramPacket sendPacket = new DatagramPacket(sendData,
                     sendData.length, group, port);
               serverSocket.send(sendPacket);
               Thread.sleep(DELAY_BETWEEN_MESSAGES);
            }
         } catch (Exception e) {
            LOGGER.error(null, e);
         }
      } catch (Exception e) {
         LOGGER.error(null, e);
      }
   }
}
