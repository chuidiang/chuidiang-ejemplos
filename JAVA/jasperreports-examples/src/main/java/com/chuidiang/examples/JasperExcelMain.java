package com.chuidiang.examples;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * @author Chuidiang
 * @date 25/10/2023
 */
public class JasperExcelMain {
    public static void main(String[] args) throws JRException {
        JasperPrint report = Base.doStuffing("src/main/files/ReportExample.jrxml", null);

        // Exportar el informe a formato Excel
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(report));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("Informe.xlsx"));

        exporter.exportReport();
    }
}
