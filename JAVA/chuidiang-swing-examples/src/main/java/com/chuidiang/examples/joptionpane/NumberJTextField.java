package com.chuidiang.examples.joptionpane;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

/**
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
        if ("".equals(getText())){
            return null;
        }
        if (OnlyNumbersDocumentFilter.REGEX_INT.equals(regex)) {
            return Integer.parseInt(getText());
        } else {
            return Double.parseDouble(getText());
        }
    }
}
