package com.chuidiang.ejemplos.java_nio.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientRunnable implements Runnable {
   private int port;
   private InetAddress group;
   private static final Logger LOGGER = LoggerFactory.getLogger(ClientRunnable.class);
   
   public ClientRunnable(int port, InetAddress group) {
      this.port=port;
      this.group=group;
   }
   public void run() {
      MulticastSocket multicastSocket = null;
      try {
          multicastSocket = new MulticastSocket(port);
          multicastSocket.joinGroup(group);
          while (true) {
              try {
                  byte[] receiveData = new byte[256];
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  multicastSocket.receive(receivePacket);
                  byte[] data = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());
                  LOGGER.info("Client received from : " + receivePacket.getAddress() + ", " + new String(data));
              } catch (Exception e) {
                  LOGGER.error(null, e);
              }
          }
      } catch (Exception e) {
          LOGGER.error(null, e);
      } finally {
          multicastSocket.close();
      }
  }
}
