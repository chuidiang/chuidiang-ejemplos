package com.chuidiang.examples.drag_and_drop;

import javax.swing.*;
import java.awt.dnd.*;

/**
 * @author fjabellan 26/10/2023
 */
public class JTextFieldDragSourceListener implements DragSourceListener {
    private final JTextField textField;

    public JTextFieldDragSourceListener(JTextField textField){
        this.textField = textField;
    }
    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
//        System.out.println("drag enter");
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
//        System.out.println("drag over");
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
//        System.out.println("drop changed");
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
//        System.out.println("drag exit");
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        if (dsde.getDropAction() == DnDConstants.ACTION_MOVE){
            textField.setText("");
        }
//        System.out.println("drop end");
    }
}
