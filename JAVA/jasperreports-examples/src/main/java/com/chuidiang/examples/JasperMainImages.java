package com.chuidiang.examples;

import net.sf.jasperreports.engine.JRException;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JasperMainImages {
    public static void main(String[] args) throws JRException, IOException {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("the_code","UA502");
        parameters.put("from_code_image",new ImageIcon("src/main/files/the_image.png"));

        Base.doStuffing("src/main/files/ReportExampleImages.jrxml", parameters);
    }
}
