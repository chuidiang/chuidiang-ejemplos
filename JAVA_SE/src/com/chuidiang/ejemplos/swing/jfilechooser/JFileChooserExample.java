package com.chuidiang.ejemplos.swing.jfilechooser;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chuidiang
 * date 16/03/2024
 */
public class JFileChooserExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JFileChooser buttons");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton withFilterButton = new JButton("Filter");
        frame.getContentPane().add(withFilterButton);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        withFilterButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new JpgFilter());
            final int selection = fileChooser.showOpenDialog((Component) e.getSource());
            if (selection == JFileChooser.APPROVE_OPTION){
                System.out.println(String.format("Seleccionado %s", fileChooser.getSelectedFile().getName()));
            }
        });
    }
}
