package com.chuidiang.examples;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Obtener un informe Jasper Report a partir de una fuente de datos CSV.
 * @author Chuidiang
 * @date 28/10/2023
 */
public class JasperFromBeanMain {
    public static void main(String[] args) throws JRException, UnsupportedEncodingException {

        // Crear un objeto JRBeanCollectionDataSource para proporcionar los datos al informe
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(FactoryBean.create());

        // Compilar el informe a partir del archivo .jasper
        JasperReport report = JasperCompileManager.compileReport(
                "src/main/files/ReportExampleBean.jrxml");

        // Parámetros, si los tienes
        HashMap<String, Object> params = new HashMap<>();

        // Generar el informe
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);

        // Mostrar el informe en una ventana
        JasperViewer.viewReport(jasperPrint);
    }
}
