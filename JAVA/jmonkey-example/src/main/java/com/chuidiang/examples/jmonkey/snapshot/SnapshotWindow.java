package com.chuidiang.examples.jmonkey.snapshot;

import javax.swing.*;
import java.awt.*;

public class SnapshotWindow {
    private final IfzSnapshotAction snapshotAction;
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JButton button = new JButton("Snapshot");

    public SnapshotWindow(IfzSnapshotAction snapshotAction){
        this.snapshotAction = snapshotAction;
        frame.getContentPane().add(label);

        frame.getContentPane().add(button, BorderLayout.NORTH);
        button.addActionListener(event -> {
            snapshotAction.takeSnapshot(image ->{
                label.setIcon(new ImageIcon(image));
            });

        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);

    }
}
