package com.chuidiang.examples;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chuidiang
 * @date 06/08/2023
 */
public class JBoxLayoutExample {
    public static void main(String[] args) {
        // Se crea la ventana con el BoxLayout
        JFrame v = new JFrame();
        v.getContentPane().setLayout(new BoxLayout(v.getContentPane(),BoxLayout.Y_AXIS));

        // Se crea un botón centrado y se añade
        JButton boton = new JButton("B");
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        v.getContentPane().add(boton);

        v.getContentPane().add(Box.createVerticalGlue());
        // Se crea una etiqueta centrada y se añade
        JLabel etiqueta = new JLabel("una etiqueta larga");
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        v.getContentPane().add(etiqueta);

        // Visualizar la ventana
        v.pack();
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
