package com.chuidiang.examples.joptionpane;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author fjabellan 16/10/2023
 */
public class JOptionPaneExamples {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JOptionPane Examples");
        frame.getContentPane().setLayout(new FlowLayout());

        addBoolean(frame);
        addNumber(frame);
        addRadioButton(frame);
        addLogin(frame);
        addPersonalization(frame);

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void addPersonalization(JFrame frame) {
        JButton customButton = new JButton("Custom");
        frame.getContentPane().add(customButton);
        customButton.addActionListener(action -> {
            JOptionPane optionPane = new JOptionPane("¿Quieres guardar el fichero?",
                    JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,
                    new ImageIcon("src/main/files/Actions-document-save-icon.png"));
            JDialog dialog = optionPane.createDialog(customButton, "Salvar fichero");
            Util.setBackgroundColor(dialog, Color.LIGHT_GRAY);
            dialog.setVisible(true);
            if (null!=optionPane.getValue() &&JOptionPane.OK_OPTION == (Integer)optionPane.getValue()){
                System.out.println("El usuario quiere salvar fichero");
            } else {
                System.out.println("El usuario ha cancelado");
            }
            dialog.dispose();
        });

    }

    private static void addLogin(JFrame frame) {
        JButton loginButton = new JButton("Login");
        frame.getContentPane().add(loginButton);
        LoginForm loginForm = new LoginForm();
        loginButton.addActionListener(action -> {
            JOptionPane optionPane = new JOptionPane(loginForm, JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog(loginButton, "Login");
            dialog.setVisible(true);
            if (null!=optionPane.getValue() && JOptionPane.OK_OPTION == (Integer)optionPane.getValue()){
                System.out.println("El usuario ha introducido usuario = " + loginForm.getUserName()+
                        "  password = "+ Arrays.toString(loginForm.getPassword()));
            } else {
                System.out.println("El usuario ha cancelado");
            }
            dialog.dispose();
        });


    }

    private static void addRadioButton(JFrame frame) {
        JButton radioButton = new JButton("Radio");
        frame.getContentPane().add(radioButton);
        RadioButtonPanel radioButtonPanel = new RadioButtonPanel();
        radioButton.addActionListener(action -> {
            JOptionPane optionPane = new JOptionPane(radioButtonPanel, JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog(radioButtonPanel, "Introducir Color");
            dialog.setVisible(true);
            if (null!=optionPane.getValue() && JOptionPane.OK_OPTION == (Integer)optionPane.getValue()){
                System.out.println("El usuario ha introducido " + radioButtonPanel.getSelectedColor());
            } else {
                System.out.println("El usuario ha cancelado");
            }
            dialog.dispose();
        });

    }

    private static void addNumber(JFrame frame) {
        JButton numberButton = new JButton("Number");
        frame.getContentPane().add(numberButton);
        NumberJTextField textField = new NumberJTextField();
        numberButton.addActionListener(action -> {
                    JOptionPane optionPane = new JOptionPane(textField, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    JDialog dialog = optionPane.createDialog(textField,"Introducir Numero");
                    dialog.setVisible(true);
                    if (null!=optionPane.getValue() && JOptionPane.OK_OPTION == (Integer)optionPane.getValue()){
                        System.out.println("El usuario ha introducido " + textField.getValue());
                    } else {
                        System.out.println("El usuario ha cancelado");
                    }
                    dialog.dispose();
                });
    }

    private static void addBoolean(JFrame frame) {
        JButton booleanButton = new JButton("Boolean");
        frame.getContentPane().add(booleanButton);
        booleanButton.addActionListener(action -> {
            Boolean selectedValue = (Boolean)JOptionPane.showInputDialog(booleanButton, "Boolean", "Selecciona Boolean",
                    JOptionPane.QUESTION_MESSAGE, null, new Boolean[]{Boolean.TRUE, Boolean.FALSE},
                    Boolean.TRUE);
            if (null==selectedValue){
                System.out.println("Se ha cancelado");
            } else {
                System.out.println("Valor seleccionado "+selectedValue);
            }
        });
    }
}
