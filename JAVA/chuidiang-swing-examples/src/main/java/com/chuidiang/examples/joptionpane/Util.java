package com.chuidiang.examples.joptionpane;

import java.awt.*;

/**
 * @author fjabellan 16/10/2023
 */
public class Util {
    public static void setBackgroundColor (Component component, Color color){
        component.setBackground(color);
        if (component instanceof Container){
            for (Component component1 : ((Container) component).getComponents()) {
                setBackgroundColor(component1, color);
            }
        }
    }
}
