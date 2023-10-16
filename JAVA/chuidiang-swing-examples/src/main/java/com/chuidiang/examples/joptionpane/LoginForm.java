package com.chuidiang.examples.joptionpane;

import javax.swing.*;
import java.awt.*;

/**
 * @author fjabellan 16/10/2023
 */
public class LoginForm extends JPanel {

    private final JTextField nameField;
    private final JPasswordField passwordField;

    public LoginForm(){
        super(new GridBagLayout());
        JLabel nameLabel = new JLabel("Usuario:");
        JLabel passwordLabel = new JLabel("Password:");
        nameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx=1;
        gc.gridy=1;
        gc.anchor = GridBagConstraints.WEST;
        add(nameLabel, gc);

        gc.gridx=2;
        gc.anchor = GridBagConstraints.EAST;
        add(nameField, gc);

        gc.gridx=1;
        gc.gridy=2;
        gc.anchor = GridBagConstraints.WEST;
        add(passwordLabel, gc);

        gc.gridx=2;
        gc.anchor = GridBagConstraints.EAST;
        add(passwordField, gc);
    }

    public String getUserName(){
        return nameField.getText();
    }

    public char[] getPassword(){
        return passwordField.getPassword();
    }
}
