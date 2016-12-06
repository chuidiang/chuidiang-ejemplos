package com.chuidiang.ejemplos.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;

public class TheMainWindow {
   private static String name;
   private static JTextField textToSend;
   private static IList<ChatData> chatRoom;
   private static JTextArea chatArea;

   public static void main(String[] args) {
      
      JFrame mainWindow = createWindow();
      requestUserName(mainWindow);
      initHazelcastData();
      addFunctionalityToUserInterface();
   }

   private static void addFunctionalityToUserInterface() {
      for (ChatData ChatData : chatRoom){
         chatArea.append(ChatData.toString()+"\n");
      }
      
      
      textToSend.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
            ChatData chatData = new ChatData();
            chatData.date=new Date();
            chatData.user=name;
            chatData.text = textToSend.getText();
            chatRoom.add(chatData);
            textToSend.setText("");
         }
      });
   }

   private static void initHazelcastData() {
      HazelcastInstance hazelInstance = Hazelcast.newHazelcastInstance();
      chatRoom = hazelInstance.getList("chatRoom");
      chatRoom.addItemListener(new ItemListener<ChatData>() {
         
         public void itemRemoved(ItemEvent<ChatData> arg0) {
            // TODO Auto-generated method stub
            
         }
         
         public void itemAdded(ItemEvent<ChatData> arg0) {
            chatArea.append(arg0.getItem().toString()+"\n");
            
         }
      }, true);
   }

   private static void requestUserName(JFrame mainWindow) {
      name = null;
      
      while (null==name || name.length()==0){
         name = JOptionPane.showInputDialog(mainWindow, "Your Name: ");
      }
   }

   private static JFrame createWindow() {
      JFrame mainWindow = new JFrame("Chat");
      chatArea = new JTextArea(24, 80);
      textToSend = new JTextField(80);
      
      mainWindow.getContentPane().add(chatArea);
      mainWindow.getContentPane().add(textToSend, BorderLayout.SOUTH);
      mainWindow.pack();
      mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      mainWindow.setLocationRelativeTo(null);
      mainWindow.setVisible(true);
      return mainWindow;
   }
}
