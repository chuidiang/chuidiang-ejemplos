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
        addInteger(frame);
        addDouble(frame);
        addRadioButton(frame);
        addLogin(frame);
        addPersonalization(frame);
        addBreakLine(frame);

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addBreakLine(JFrame frame) {
        JButton breakButton = new JButton("Break");
        frame.getContentPane().add(breakButton);
        breakButton.addActionListener(action -> {
            JOptionPane.showMessageDialog(breakButton, "Esta linea\nesta partida\nen tres");
            JOptionPane.showMessageDialog(breakButton, "<html>Todo esta en negrita<br>Pero podemos poner <i>cursiva</i>");
            final JLabel label = new JLabel("<html>Esta tiene <b>negrita</b><br>y <i>cursiva</i><br>y hemos personalizado la fuente</html>");
            label.setFont(new Font("Arial", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(breakButton, label);
        });

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
            if (null != optionPane.getValue() && JOptionPane.OK_OPTION == (Integer) optionPane.getValue()) {
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

        loginButton.addActionListener(action -> {
            LoginForm loginForm = new LoginForm();
            JOptionPane optionPane = new JOptionPane(loginForm, JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog(loginButton, "Login");
            dialog.setVisible(true);
            if (null != optionPane.getValue() && JOptionPane.OK_OPTION == (Integer) optionPane.getValue()) {
                System.out.println("El usuario ha introducido usuario = " + loginForm.getUserName() +
                        "  password = " + Arrays.toString(loginForm.getPassword()));
            } else {
                System.out.println("El usuario ha cancelado");
            }
            dialog.dispose();
        });


    }

    private static void addRadioButton(JFrame frame) {
        JButton radioButton = new JButton("Radio");
        frame.getContentPane().add(radioButton);

        radioButton.addActionListener(action -> {
            RadioButtonPanel radioButtonPanel = new RadioButtonPanel();
            JOptionPane optionPane = new JOptionPane(radioButtonPanel, JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog(radioButton, "Introducir Color");
            dialog.setVisible(true);
            if (null != optionPane.getValue() && JOptionPane.OK_OPTION == (Integer) optionPane.getValue()) {
                System.out.println("El usuario ha introducido " + radioButtonPanel.getSelectedColor());
            } else {
                System.out.println("El usuario ha cancelado");
            }
            dialog.dispose();
        });

    }

    private static void addInteger(JFrame frame) {
        JButton numberButton = new JButton("Integer");
        frame.getContentPane().add(numberButton);
        numberButton.addActionListener(action -> {
            NumberJTextField textField = new NumberJTextField(OnlyNumbersDocumentFilter.REGEX_INT);
            JOptionPane optionPane = new JOptionPane(textField, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog(numberButton, "Introducir Integer");
            dialog.setVisible(true);
            if (null != optionPane.getValue() && JOptionPane.OK_OPTION == (Integer) optionPane.getValue()) {
                System.out.println("El usuario ha introducido " + textField.getValue());
            } else {
                System.out.println("El usuario ha cancelado");
            }
            dialog.dispose();
        });
    }

    private static void addDouble(JFrame frame) {
        JButton numberButton = new JButton("Double");
        frame.getContentPane().add(numberButton);
        numberButton.addActionListener(action -> {
            NumberJTextField textField = new NumberJTextField(OnlyNumbersDocumentFilter.REGEX_DOUBLE);
            JOptionPane optionPane = new JOptionPane(textField, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog(numberButton, "Introducir Double");
            dialog.setVisible(true);
            if (null != optionPane.getValue() && JOptionPane.OK_OPTION == (Integer) optionPane.getValue()) {
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
            Boolean selectedValue = (Boolean) JOptionPane.showInputDialog(booleanButton, "Boolean", "Selecciona Boolean",
                    JOptionPane.QUESTION_MESSAGE, null, new Boolean[]{Boolean.TRUE, Boolean.FALSE},
                    Boolean.TRUE);
            if (null == selectedValue) {
                System.out.println("Se ha cancelado");
            } else {
                System.out.println("Valor seleccionado " + selectedValue);
            }
        });
    }
}
