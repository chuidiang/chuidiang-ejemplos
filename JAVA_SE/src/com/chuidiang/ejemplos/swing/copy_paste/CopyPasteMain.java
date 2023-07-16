package com.chuidiang.ejemplos.swing.copy_paste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Chuidiang
 * @date 12/03/2023
 */
public class CopyPasteMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JTextField textField1 = new JTextField(20);
        JTextField textField2 = new JTextField(20);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(textField1);
        frame.getContentPane().add(textField2);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        textField1.addKeyListener(new KeyAdapter() {
                                      @Override
                                      public void keyTyped(KeyEvent e) {
                                          System.out.println("typed " + e.paramString());
                                          System.out.println("keychar = " + e.getKeyChar());
                                          System.out.println("keycode = " + e.getKeyCode());
                                          if (e.getKeyChar() == 3) {
                                              System.out.println("Ctrl-c");
                                              e.consume();
                                          }
                                          if (e.getKeyChar() == 22) {
                                              System.out.println("Ctrl-v");
                                              e.consume();
                                          }
                                      }
                                  }
        );
    }
}
