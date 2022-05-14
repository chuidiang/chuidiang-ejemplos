package com.chuidiang.examples;

import javax.swing.*;
import java.awt.*;

/**
 * JFrame example
 * @author Chuidiang
 */
public class JFrameExample
{
    public static void main( String[] args )
    {
        JFrame frame = new JFrame("Main Window");

        JButton button = new JButton("Click me!");
        button.setMargin(new Insets(20,200,20,200));
        frame.getContentPane().add(button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("C:/Users/fjabe/Pictures/ing.png").getImage());
        frame.pack();

        frame.setVisible(true);
    }
}
