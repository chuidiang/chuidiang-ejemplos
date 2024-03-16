package com.chuidiang.ejemplos.swing.jfilechooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Tablas de cálculo", "csv", "xlsx"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Textos", "txt", "docx"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "gif"));
            final int selection = fileChooser.showOpenDialog((Component) e.getSource());
            if (selection == JFileChooser.APPROVE_OPTION){
                System.out.println(String.format("Seleccionado %s", fileChooser.getSelectedFile().getName()));
            }
        });
    }
}
