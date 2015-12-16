package com.chuidiang.ejemplos.fuse.bin.socket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class RouteBinMain {

   int received = 0;
   int responsed = 0;

   public static void main(String[] args) throws Exception {

      final CamelContext context = startCamel();
      startServerSocket();
      startClientSocket();

      // The window to start/stop de route doesn't work.
      // Probably it's a camel-netty issue 
      startControlWindow(context);

   }

   private static void startControlWindow(final CamelContext context) {
      JFrame frame = new JFrame();
      final JCheckBox checkBox = new JCheckBox("Pause");
      frame.getContentPane().add(checkBox);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      checkBox.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            if (checkBox.isSelected()) {
               ProducerTemplate p = context.createProducerTemplate();
               p.sendBody("controlbus:route?routeId=myRoute&action=suspend",
                     null);
            } else {
               ProducerTemplate p = context.createProducerTemplate();
               p.sendBody("controlbus:route?routeId=myRoute&action=resume",
                     null);
            }

         }
      });
   }

   private static void startClientSocket() throws UnknownHostException,
         IOException {
      Socket s = new Socket("localhost", 55559);
      System.out.println("Connected");
      new LectorThread("Client Lector", s.getInputStream(), null).start();
      new WriterThread("Client Writer", s.getOutputStream(), 1000, new byte[] {
            3, 3, 3, 0 }).start();
   }

   private static void startServerSocket() throws IOException {
      final ServerSocket ss = new ServerSocket(55560);
      new Thread() {
         public void run() {
            try {
               Socket client = ss.accept();
               new LectorThread("Server Lector", client.getInputStream(),
                     client.getOutputStream()).start();
               // new WriterThread("Server Writer", client.getOutputStream(),
               // 100, new byte[]{1,0,2,2,0}).start();
            } catch (IOException e) {

               e.printStackTrace();
            }
         }
      }.start();
   }

   private static CamelContext startCamel() throws Exception {
      SimpleRegistry registry = new SimpleRegistry();
      registry.put("FromDecoder", new MessageDecoder());
      registry.put("FromEncoder", new MessageEncoder());
      registry.put("ToDecoder", new MessageDecoder());
      registry.put("ToEncoder", new MessageEncoder());

      final CamelContext context = new DefaultCamelContext(registry);

      context.addRoutes(new RouteBuilder() {

         @Override
         public void configure() throws Exception {
             createNettyRoute();
         }

         protected void createNettyRoute() {
            from(
                  "netty:tcp://localhost:55559?encoder=#FromEncoder&"
                        + "decoder=#FromDecoder&"
                        + "disconnectOnNoReply=false&"
                        + "serverClosedChannelExceptionCaughtLogLevel=WARN&"
                        + "keepAlive=true")
            .to(
                  "netty:tcp://localhost:55560?encoder=#ToEncoder&"
                        + "decoder=#ToDecoder&" + "disconnectOnNoReply=false&"
                        + "serverClosedChannelExceptionCaughtLogLevel=WARN&"
                        + "keepAlive=true")
            .setId("myRoute");
         }

      });
      context.start();
      return context;
   }

}
