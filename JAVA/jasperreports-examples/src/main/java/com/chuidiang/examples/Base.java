package com.chuidiang.examples;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
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

        // Ver los parámetros que hay definidos en el informe y que no son
        // los que pone Jasper de por sí.
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

            // Lo exporta a excel
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "fichero.xls");
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.exportReport();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
