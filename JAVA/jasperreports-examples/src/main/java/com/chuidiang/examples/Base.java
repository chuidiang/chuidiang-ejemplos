package com.chuidiang.examples;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Chuidiang
 * @date 22/10/2023
 */
public class Base {
    private Base() {
        // Evita instancias de esta clase. Todos sus métodos son static
    }

    /**
     * Genera el informe pasandole la plantilla jrxml y los parámetros.
     * Se conecta a base de datos para coger los datos del informe.
     * @param jrxmlFile
     * @param parameters
     * @throws JRException
     */
    public static void doStuffing(String jrxmlFile, Map<String, Object> parameters) throws JRException {
        JasperReport report = JasperCompileManager.compileReport(
                jrxmlFile);

        final JRParameter[] parameters1 = report.getParameters();
        for (JRParameter jrParameter : parameters1) {
            if (jrParameter.isSystemDefined()){
                continue;
            }
            System.out.println(jrParameter.getName());
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost/chuidiang-examples","postgres", "postgres")) {
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);

            JasperExportManager.exportReportToPdfFile(print,
                    "target/report.pdf");

            //Para visualizar el pdf directamente desde java
            JasperViewer.viewReport(print, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
