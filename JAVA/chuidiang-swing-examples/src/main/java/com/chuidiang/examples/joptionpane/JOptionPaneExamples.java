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

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void addLogin(JFrame frame) {
        JButton buttonBoolean = new JButton("Login");
        frame.getContentPane().add(buttonBoolean);
        LoginForm loginForm = new LoginForm();
        buttonBoolean.addActionListener(action -> {
            JOptionPane optionPane = new JOptionPane(loginForm, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog(buttonBoolean, "Login");
            dialog.setVisible(true);
            if (null!=optionPane.getValue() && JOptionPane.OK_OPTION == (Integer)optionPane.getValue()){
                System.out.println("El usuario ha introducido usuario = " + loginForm.getUserName()+ "  password = "+ Arrays.toString(loginForm.getPassword()));
            } else {
                System.out.println("El usuario ha cancelado");
            }
            dialog.dispose();
        });


    }

    private static void addRadioButton(JFrame frame) {
        JButton buttonBoolean = new JButton("Radio");
        frame.getContentPane().add(buttonBoolean);
        RadioButtonPanel radioButtonPanel = new RadioButtonPanel();
        buttonBoolean.addActionListener(action -> {
            JOptionPane optionPane = new JOptionPane(radioButtonPanel, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
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
        JButton buttonBoolean = new JButton("Number");
        frame.getContentPane().add(buttonBoolean);
        NumberJTextField textField = new NumberJTextField();
        buttonBoolean.addActionListener(action -> {
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
        JButton buttonBoolean = new JButton("Boolean");
        frame.getContentPane().add(buttonBoolean);
        buttonBoolean.addActionListener(action -> {
            Boolean selectedValue = (Boolean)JOptionPane.showInputDialog(buttonBoolean, "Boolean", "Selecciona Boolean",
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
