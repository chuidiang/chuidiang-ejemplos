package com.chuidiang.examples.joptionpane;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * @author fjabellan 16/10/2023
 */
public class OnlyNumbersDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        System.out.println(fb.getDocument().getText(0, fb.getDocument().getLength()));
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        System.out.println(fb.getDocument().getText(0, fb.getDocument().getLength()));
        super.remove(fb, offset, length);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        System.out.println(fb.getDocument().getText(0, fb.getDocument().getLength()));
        super.replace(fb, offset, length, text, attrs);
    }
}
