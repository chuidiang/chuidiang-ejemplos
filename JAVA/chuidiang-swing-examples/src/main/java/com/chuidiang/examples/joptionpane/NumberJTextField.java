package com.chuidiang.examples.joptionpane;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

/**
 * @author fjabellan 16/10/2023
 */
public class NumberJTextField extends JTextField {
    public NumberJTextField () {
        super(20);
        ((AbstractDocument)getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
    }

    public Integer getValue (){
        if ("".equals(getText())){
            return null;
        }
        return Integer.parseInt(getText());
    }
}
