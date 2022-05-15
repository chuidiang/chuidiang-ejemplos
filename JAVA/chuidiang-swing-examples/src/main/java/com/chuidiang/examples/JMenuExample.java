package com.chuidiang.examples;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * JFrame example
 * @author Chuidiang
 */
public class JMenuExample
{
    public static void main( String[] args )
    {
        JFrame frame = new JFrame("Main Window");

        JButton button = new JButton("Click me!");
        button.setMargin(new Insets(20,200,20,200));
        frame.getContentPane().add(button);

        frame.setJMenuBar(createMenuBar());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();

        frame.setVisible(true);
    }


    private static JMenuBar createMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem(createSaveAction());
        JMenuItem saveAsItem = new JMenuItem("Save As");
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);

        fileMenu.add(new JSeparator());

        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        jMenuBar.add(fileMenu);

        JMenu aboutMenu = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        aboutMenu.add(about);
        jMenuBar.add(Box.createHorizontalGlue());
        jMenuBar.add(aboutMenu);

        return  jMenuBar;
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
