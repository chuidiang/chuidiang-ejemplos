package com.chuidiang.ejemplos.checks_radios;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class ToggleButtonExample {

   private static JToggleButton check;
   private static JToggleButton check2;

   public static void main(String[] args) {
      JFrame frame = new JFrame("JToggleButton Example");
      check = new JToggleButton("Push here ");
      check2 = new JToggleButton("I'm a mirror");
      
      frame.getContentPane().add(check);
      frame.getContentPane().setLayout(new FlowLayout());
      frame.getContentPane().add(check2);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      
      check.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent event) {
            check2.setSelected(check.isSelected());
         }
      });
      
      
   }

}
