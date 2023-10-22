package com.chuidiang.examples;

import net.sf.jasperreports.engine.JRException;

/**
 * @author Chuidiang
 * @date 22/10/2023
 */
public class JasperMain {
    public static void main(String[] args) throws JRException {
        Base.doStuffing("src/main/files/ReportExample.jrxml", null);
    }
}
