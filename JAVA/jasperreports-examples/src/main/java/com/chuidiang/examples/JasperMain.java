package com.chuidiang.examples;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JasperMain {
    public static void main(String[] args) throws JRException, ClassNotFoundException, SQLException {
        JasperReport report = JasperCompileManager.compileReport(
                "src/main/files/Blank_A4.jrxml");

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("the_code","UA502");
        parameters.put("the_image","src/main/files/the_image.png");

        Class.forName("org.postgresql.Driver");
        Connection conn = null;
        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost/chuidiang-examples","postgres", "postgres");
        JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);

        JasperExportManager.exportReportToPdfFile(print,
                "target/Blank_A4.pdf");

        //Para visualizar el pdf directamente desde java
        JasperViewer.viewReport(print, false);
    }
}
