package com.chuidiang.examples.luciad.business_data.lsp;

import com.luciad.view.swing.ALcdBalloonDescriptor;
import com.luciad.view.swing.ILcdBalloonContentProvider;

import javax.swing.*;

public class MyBalloonProvider implements ILcdBalloonContentProvider {
    @Override
    public boolean canGetContent(ALcdBalloonDescriptor aLcdBalloonDescriptor) {
        return true;
    }

    @Override
    public JComponent getContent(ALcdBalloonDescriptor aLcdBalloonDescriptor) {
        JLabel theLabel= new JLabel("Soy el balloon");
        theLabel.setOpaque(false);
        return theLabel;
    }
}
