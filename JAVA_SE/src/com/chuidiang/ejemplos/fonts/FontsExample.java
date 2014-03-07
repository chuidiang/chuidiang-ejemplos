package com.chuidiang.ejemplos.fonts;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Example of Fonts.
 * @author Chuidiang
 *
 */
public class FontsExample {

   public static void main(String[] args) throws InterruptedException {
      // List all available fonts.
      String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
      System.out.println(Arrays.toString(fontNames));
      
      // A windows with a label.
      JFrame frame = new JFrame("Fonts Example");
      JLabel label = new JLabel();
      label.setHorizontalAlignment(SwingConstants.CENTER);
      frame.getContentPane().add(label);
      frame.setSize(400,100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      
      // Show a text for each font
      for (String font:fontNames){
         label.setText("Hi, I'm "+font);
         label.setFont(new Font(font,Font.PLAIN,15));
         Thread.sleep(1000);
      }
   }

}
