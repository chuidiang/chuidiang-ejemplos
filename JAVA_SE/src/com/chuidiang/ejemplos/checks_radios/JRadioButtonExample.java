package com.chuidiang.ejemplos.checks_radios;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class JRadioButtonExample {

   private static JRadioButton check;
   private static JRadioButton check2;

   public static void main(String[] args) throws InterruptedException {
      JFrame frame = new JFrame("JRadioButton Example");
      check = new JRadioButton("Check here ");
      check2 = new JRadioButton("Or check here");
      ButtonGroup bg = new ButtonGroup();
      bg.add(check);
      bg.add(check2);
      
      frame.getContentPane().add(check);
      frame.getContentPane().setLayout(new FlowLayout());
      frame.getContentPane().add(check2);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      
      Thread.sleep(2000);
      bg.clearSelection();
   }

}
