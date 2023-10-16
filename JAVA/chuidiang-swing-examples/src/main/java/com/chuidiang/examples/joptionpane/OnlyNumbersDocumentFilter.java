package com.chuidiang.examples.joptionpane;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Pattern;

/**
 * @author fjabellan 16/10/2023
 */
public class OnlyNumbersDocumentFilter extends DocumentFilter {

    public static final String REGEX_INT = "^[+-]?\\d*$";
    public static final String REGEX_DOUBLE = "^[-+]?[0-9]*\\.?[0-9]*$";
    private final String regex;

    public OnlyNumbersDocumentFilter(String regex) {
        this.regex = regex;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String insertString, AttributeSet attr) throws BadLocationException {
        String textBefore = fb.getDocument().getText(0, offset);
        String textAfter = fb.getDocument().getText(offset, fb.getDocument().getLength() - offset);
        String fullText = textBefore + insertString + textAfter;
        if (Pattern.matches(regex, fullText)) {
            super.insertString(fb, offset, insertString, attr);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        String textBefore = fb.getDocument().getText(0, offset);
        String textAfter = fb.getDocument().getText(offset + length, fb.getDocument().getLength() - offset - length);
        String fullText = textBefore + textAfter;
        if (Pattern.matches(regex, fullText)) {
            super.remove(fb, offset, length);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String updatedText, AttributeSet attrs) throws BadLocationException {

        String textBefore = fb.getDocument().getText(0, offset);
        String textAfter = fb.getDocument().getText(offset + length, fb.getDocument().getLength() - offset - length);
        String fullText = textBefore + updatedText + textAfter;
        if (Pattern.matches(regex, fullText)) {
            super.replace(fb, offset, length, updatedText, attrs);
        }
    }
}
