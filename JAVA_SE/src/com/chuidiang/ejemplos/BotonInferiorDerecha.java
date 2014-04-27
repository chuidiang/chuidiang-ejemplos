package com.chuidiang.ejemplos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Simple example. How to locate a button in the bottom right corner of a window
 * 
 * @author Chuidiang
 */
public class BotonInferiorDerecha {

   public static void main(String[] args) {
      panelConFlowLayout();
      conGridBagLayout();
   }

   private static void conGridBagLayout() {
      JFrame v = new JFrame();
      v.getContentPane().setLayout(new GridBagLayout());
      JLabel dummy = new JLabel();
      GridBagConstraints gc = new GridBagConstraints();
      gc.gridx=0;
      gc.gridy=0;
      gc.weightx=1.0;
      gc.weighty=1.0;
      v.getContentPane().add(dummy,gc);
      gc.gridx=1;
      gc.gridy=1;
      gc.weightx=0.0;
      gc.weighty=0.0;
      v.getContentPane().add(new JButton("el boton"),gc);
      v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      v.pack();
      v.setVisible(true);
   }

   private static void panelConFlowLayout() {
      JFrame v = new JFrame();
      JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
      panel2.add(new JButton("el boton"));
      v.getContentPane().add(panel2, BorderLayout.SOUTH);
      v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      v.pack();
      v.setVisible(true);
   }
}
