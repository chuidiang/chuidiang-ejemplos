package com.chuidiang.examples.git_log;

import javafx.scene.control.SelectionMode;

import javax.swing.*;
import java.io.File;

/**
 * Created by JAVIER on 25/02/2017.
 */
public class TheWindow extends JFrame{
    private File selectedFile=null;

    public TheWindow(){
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        JButton chooseRepository = new JButton("Repositorio");
        addChooseRepositoryListener(chooseRepository);
        getContentPane().add(chooseRepository);

        JTextField pattern = new JTextField(20);
        getContentPane().add(pattern);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addChooseRepositoryListener(JButton chooseRepository) {
        chooseRepository.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser();
            if (null!=selectedFile){
                fileChooser.setCurrentDirectory(selectedFile);
            }
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(this);
            selectedFile = fileChooser.getSelectedFile();
        });
    }
}
