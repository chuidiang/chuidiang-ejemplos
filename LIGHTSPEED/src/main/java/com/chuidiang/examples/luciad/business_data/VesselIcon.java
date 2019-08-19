package com.chuidiang.examples.luciad.business_data;

import com.luciad.gui.ILcdIcon;

import java.awt.*;

public class VesselIcon implements ILcdIcon {
    @Override
    public void paintIcon(Component component, Graphics graphics, int x, int y) {
        graphics.setColor(Color.RED);
        graphics.drawLine(x,y+15,x+5,y);
        graphics.drawLine(x+10,y+15,x+5,y);
        graphics.drawLine(x,y+15,x+10,y+15);
    }

    @Override
    public int getIconWidth() {
        return 11;
    }

    @Override
    public int getIconHeight() {
        return 16;
    }

    @Override
    public Object clone() {
        return new VesselIcon();
    }
}
