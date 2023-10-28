package com.chuidiang.examples.drag_and_drop;

import com.chuidiang.examples.UtilJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;

/**
 * @author fjabellan 26/10/2023
 */
public class DragAndDropExample {
    public static void main(String[] args) {
        JFrame frame = UtilJFrame.newJFrame("Drag And Drop Example");

        JTextField textField1 = new JTextField(20);
        JTextField textField2 = new JTextField(20);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(textField1);
        panel.add(textField2);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(
                textField1,
                DnDConstants.ACTION_COPY|DnDConstants.ACTION_MOVE|DnDConstants.ACTION_LINK,
                new JTextFieldDragGestureListener(
                        textField1, new JTextFieldDragSourceListener(textField1)));


        DropTarget dropTarget = new DropTarget(textField2, new JTextFieldDropTargetListener(textField2));
    }
}
