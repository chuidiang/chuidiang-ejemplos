package com.chuidiang.examples.joptionpane;

import javax.swing.*;

/**
 * @author fjabellan 16/10/2023
 */
public class NumberJTextField extends JTextField {
    public NumberJTextField () {
        super(20);
        setDocument(new OnlyNumbersDocument());
    }

    public Integer getValue (){
        if ("".equals(getText())){
            return null;
        }
        return Integer.parseInt(getText());
    }
}
