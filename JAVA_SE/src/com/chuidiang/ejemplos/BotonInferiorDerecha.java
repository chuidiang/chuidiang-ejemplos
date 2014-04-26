package com.chuidiang.ejemplos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Simple example. How to locate a button in the bottom right corner of a window
 * 
 * @author Chuidiang
 */
public class BotonInferiorDerecha {

   public static void main(String[] args) {
      JFrame v = new JFrame();
      JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
      panel2.add(new JButton("el boton"));
      v.getContentPane().add(panel2, BorderLayout.SOUTH);
      v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      v.pack();
      v.setVisible(true);
   }
}
