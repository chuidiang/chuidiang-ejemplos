package com.chuidiang.examples.joptionpane;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

/**
 * Editor que obliga a que lo que se escriba en él cumpla una expresión regular.
 * Esta pensado para enteros y doubles, por lo que el metodo getValue() es muy
 * especifico para eso.
 * @author fjabellan 16/10/2023
 */
public class NumberJTextField extends JTextField {
    private final String regex;

    public NumberJTextField (String regex) {
        super(20);
        this.regex = regex;
        ((AbstractDocument)getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter(regex));
    }

    public Number getValue (){
        String text = getText();
        if ("".equals(text)){
            return null;
        }
        if (".".equals(text) || "+.".equals(text) || "-.".equals(text)){
            return 0.0;
        }
        if ("+".equals(text) || "-".equals(text)){
            return 0;
        }
        if (OnlyNumbersDocumentFilter.REGEX_INT.equals(regex)) {
            return Integer.parseInt(getText());
        } else {
            return Double.parseDouble(getText());
        }
    }
}
