package com.chuidiang.examples.drag_and_drop;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;

/**
 * @author fjabellan 26/10/2023
 */
public class JTextFieldDragGestureListener implements DragGestureListener {

    private final JTextField textField;
    private final JTextFieldDragSourceListener sourceListener;

    public JTextFieldDragGestureListener(JTextField textField, JTextFieldDragSourceListener sourceListener){
        this.textField = textField;
        this.sourceListener = sourceListener;
    }
    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        StringSelection string = new StringSelection(textField.getText());
        dge.startDrag(null, string, sourceListener);
    }
}
