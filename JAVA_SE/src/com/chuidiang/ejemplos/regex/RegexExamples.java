package com.chuidiang.ejemplos.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JAVIER on 19/04/2017.
 */
public class RegexExamples {
    public static void main(String[] args) {

        System.out.println("Date Examples");
        dateExamples();

        System.out.println("DNI examples");
        dniExamples();

        System.out.println("email examples");
        emailExamples();
    }

    private static void emailExamples() {
        String emailRegexp = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";

        // Lo siguiente devuelve true
        System.out.println(Pattern.matches(emailRegexp, "a@b.com"));
        System.out.println(Pattern.matches(emailRegexp, "+++@+++.com"));

        // Lo siguiente devuelve faslse
        System.out.println(Pattern.matches(emailRegexp, "@b.com")); // Falta el nombre
        System.out.println(Pattern.matches(emailRegexp, "a@b.c")); // El dominio final debe tener al menos dos letras

        String entrada = "<p>hola@pedro.com</p><br>\n";
        entrada += "kk@tres.tris///pepe@eso.es";

        Pattern limpiar = Pattern
                .compile("([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)");
        Matcher buscar = limpiar.matcher(entrada);
        while (buscar.find())
            System.out.println(buscar.group(1));

    }

    private static void dniExamples() {
        String dniRegexp = "\\d{8}[A-HJ-NP-TV-Z]";

        // Lo siguiente devuelve true
        System.out.println(Pattern.matches(dniRegexp, "01234567C"));

        // Lo siguiente devuelve faslse
        System.out.println(Pattern.matches(dniRegexp, "01234567U")); // La U no es válida
        System.out.println(Pattern.matches(dniRegexp, "0123567X")); // No tiene 8 cifras
    }

    private static void dateExamples() {
        String numericDateRegexp = "\\d{1,2}/\\d{1,2}/\\d{4}";

        // Lo siguiente devuelve true
        System.out.println(Pattern.matches(numericDateRegexp, "11/12/2014"));
        System.out.println(Pattern.matches(numericDateRegexp, "1/12/2014"));
        System.out.println(Pattern.matches(numericDateRegexp, "11/2/2014"));


        // Los siguientes devuelven false
        System.out.println(Pattern.matches(numericDateRegexp, "11/12/14"));
        System.out.println(Pattern.matches(numericDateRegexp, "11//2014"));
        System.out.println(Pattern.matches(numericDateRegexp, "11/12/2014perico"));

        String literalMonthRegexp = "\\d{1,2}/(?i)(ene|feb|mar|abr|may|jun|jul|ago|sep|oct|nov|dic)/\\d{4}";

        // Lo siguiente devuelve true
        System.out.println(Pattern.matches(literalMonthRegexp, "11/dic/2014"));
        System.out.println(Pattern.matches(literalMonthRegexp, "1/nov/2014"));
        System.out.println(Pattern.matches(literalMonthRegexp, "1/AGO/2014"));
        System.out.println(Pattern.matches(literalMonthRegexp, "21/Oct/2014"));

        // Los siguientes devuelven false
        System.out.println(Pattern.matches(literalMonthRegexp, "11/abc/2014"));
        System.out.println(Pattern.matches(literalMonthRegexp, "11//2014"));
        System.out.println(Pattern.matches(literalMonthRegexp, "11/jul/2014perico"));
    }
}
