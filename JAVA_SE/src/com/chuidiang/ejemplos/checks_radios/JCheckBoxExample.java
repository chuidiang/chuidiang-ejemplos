package com.chuidiang.ejemplos.checks_radios;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class JCheckBoxExample {

   private static JCheckBox check;
   private static JCheckBox check2;

   public static void main(String[] args) {
      JFrame frame = new JFrame("JCheckBox Example");
      check = new JCheckBox("Check here ", new ImageIcon("C:/Users/BEEP/Pictures/eclipse-debugger-continuar.png"));
      check2 = new JCheckBox("I'm a mirror");
      
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
            check2.setSelected(((JCheckBox)event.getSource()).isSelected());
         }
      });
      
      
   }

}
