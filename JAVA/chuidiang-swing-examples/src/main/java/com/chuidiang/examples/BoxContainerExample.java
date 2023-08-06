package com.chuidiang.examples;

import javax.swing.*;

/**
 * @author Chuidiang
 * @date 06/08/2023
 */
public class BoxContainerExample {
    public static void main(String[] args) {
        // Se crea la ventana con el BoxLayout
        JFrame v = new JFrame();
        Box box = Box.createHorizontalBox();

        // Se crea un botón centrado y se añade
        JButton boton = new JButton("B");
        box.add(boton);

        box.add(Box.createHorizontalGlue());

        // Se crea una etiqueta centrada y se añade
        JLabel etiqueta = new JLabel("una etiqueta larga");
        box.add(etiqueta);

        v.getContentPane().add(box);

        // Visualizar la ventana
        v.pack();
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
