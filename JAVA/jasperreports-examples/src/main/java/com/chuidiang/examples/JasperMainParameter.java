package com.chuidiang.examples;

import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JasperMainParameter {
    public static void main(String[] args) throws JRException, SQLException {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("Usuario","Pedro");

        Base.doStuffing("src/main/files/ReportExampleParameter.jrxml", parameters);
    }
}
