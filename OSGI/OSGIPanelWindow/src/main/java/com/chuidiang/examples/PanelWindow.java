package com.chuidiang.examples;

import javax.swing.*;

/**
 * Created by JAVIER on 11/03/2017.
 */
public class PanelWindow extends JPanel implements IfzPanelWindow {
    public PanelWindow(){
        JLabel label = new JLabel("Hello World");
        add(label);
    }

    public JPanel getPanel() {
        return this;
    }

    public void start() {
        JOptionPane.showMessageDialog(null,"PanelWindow starts");
        System.out.println("PanelWindow starts");
    }

    public void stop(){
        System.out.println("PanelWindow stops");
    }
}
