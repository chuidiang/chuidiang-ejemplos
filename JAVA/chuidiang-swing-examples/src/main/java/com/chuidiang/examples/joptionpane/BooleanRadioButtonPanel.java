package com.chuidiang.examples.joptionpane;

import javax.swing.*;
import java.awt.*;

/**
 * Panel con tres radio button, uno para cada color básico.
 * @author fjabellan 16/10/2023
 */
public class BooleanRadioButtonPanel extends JPanel {

    private final ButtonGroup group;

    public BooleanRadioButtonPanel(){
        super(new FlowLayout());
        JRadioButton radio1 = new JRadioButton("TRUE", true);
        radio1.setActionCommand("TRUE");
        JRadioButton radio2 = new JRadioButton("FALSE");
        radio2.setActionCommand("FALSE");
        group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);

        add(radio1);
        add(radio2);
    }

    public Boolean getBooleanValue(){
        switch (group.getSelection().getActionCommand()){
            case "TRUE":
                return Boolean.TRUE;
            case "FALSE":
                return Boolean.FALSE;
            default:
                return null;
        }
    }
}
