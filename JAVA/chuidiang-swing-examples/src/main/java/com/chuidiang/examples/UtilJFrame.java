package com.chuidiang.examples;

import javax.swing.*;

/**
 * @author fjabellan 26/10/2023
 */
public class UtilJFrame {
    private UtilJFrame(){
        // Evita constructores
    }

    public static JFrame newJFrame(String title){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        return frame;
    }
}
