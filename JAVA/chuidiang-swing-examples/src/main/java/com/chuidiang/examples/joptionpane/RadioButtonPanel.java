package com.chuidiang.examples.joptionpane;

import javax.swing.*;
import java.awt.*;

/**
 * @author fjabellan 16/10/2023
 */
public class RadioButtonPanel extends JPanel {

    private final ButtonGroup group;

    public RadioButtonPanel(){
        super(new FlowLayout());
        JRadioButton radio1 = new JRadioButton("Rojo", true);
        radio1.setActionCommand("Rojo");
        JRadioButton radio2 = new JRadioButton("Verde");
        radio2.setActionCommand("Verde");
        JRadioButton radio3 = new JRadioButton("Azul");
        radio3.setActionCommand("Azul");
        group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);

        add(radio1);
        add(radio2);
        add(radio3);
    }

    public Color getSelectedColor(){
        switch (group.getSelection().getActionCommand()){
            case "Rojo":
                return Color.RED;
            case "Verde":
                return Color.GREEN;
            case "Azul":
                return Color.BLUE;
        }
        return null;

    }
}
