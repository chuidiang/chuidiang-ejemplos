package com.chuidiang.examples;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.Arrays;

/**
 * @author Chuidiang
 * @date 29/10/2023
 */
public class CustonDataSource implements JRDataSource {
    private int counter = 0;

    @Override
    public boolean next() throws JRException {
        return counter++ < 1;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        System.out.println(jrField.getName());
        System.out.println(Arrays.toString(jrField.getPropertyExpressions()));
        return jrField.getName() + " - " + counter;
    }
}
