package com.chuidiang.examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author fjabellan
 * @date 21/05/2022
 */
public class JToolBarExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Window");

        JButton button = new JButton("Click me!");
        button.setMargin(new Insets(20,200,20,200));
        frame.getContentPane().add(button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("C:/Users/fjabe/Pictures/ing.png").getImage());

        addToolBar(frame);

        frame.pack();
        frame.setVisible(true);
    }

    private static void addToolBar(JFrame frame) {
        JToolBar toolBar = new JToolBar();
        toolBar.add(createSaveAction());
        toolBar.add(new JButton("2"));
        toolBar.add(new JButton("3"));
        toolBar.add(new JButton("4"));
        frame.getContentPane().add(toolBar,BorderLayout.NORTH);
    }

    private static Action createSaveAction(){
        Action saveAction = new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save clicked");
            }
        };
        saveAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
        saveAction.putValue(Action.SHORT_DESCRIPTION,"Save the file");
        saveAction.putValue(Action.SMALL_ICON,new ImageIcon("src/main/files/Actions-document-save-icon.png"));

        return saveAction;
    }

}
