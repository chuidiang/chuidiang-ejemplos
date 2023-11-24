package com.chuidiang.ejemplos.numbers;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * @author Chuidiang
 * date 19/11/2023
 */
public class DecimalFormatExamples {
    public static void main(String[] args) throws ParseException {
        long a = 1234557;
        double b = 1112333344567.4566;

        DecimalFormat df = new DecimalFormat("##.##");
        System.out.println(df.format(a));  // 123455
        System.out.println(df.format(b));  // 1234,46

        df = new DecimalFormat("############.##########");
        System.out.println(df.format(a));  // 123455
        System.out.println(df.format(b));  // 1234,46

        df = new DecimalFormat("000000000000.0000000000000");
        System.out.println(df.format(a));  // 000000123455,0000000000000
        System.out.println(df.format(b));  // 000000001234,4566000000000

        df = new DecimalFormat("#,###.##");
        System.out.println(df.format(a));  // 123.455
        System.out.println(df.format(b));  // 1.234,46

        df = new DecimalFormat("#.##E0");
        System.out.println(df.format(1234567));  // 1,23E6
        System.out.println(df.format(123456789012.3456)); // 1,23E11

        df = new DecimalFormat("00.00%");
        System.out.println(df.format(1));  // 100,00%
        System.out.println(df.format(0.94)); // 94,00%

        df = new DecimalFormat("00.00\u2030");
        System.out.println(df.format(1));  // 1000,00‰
        System.out.println(df.format(0.94)); // 940,00‰


        df = new DecimalFormat("#.00\u00A4");
        System.out.println(df.format(12));  // 11,94€
        System.out.println(df.format(11.94)); // 11,94€

        df = new DecimalFormat("#.00¤¤");
        System.out.println(df.format(12));  // 12,00EUR
        System.out.println(df.format(11.94)); // 11,94EUR

        df = new DecimalFormat("#.##%;menos # puff");
        System.out.println(df.format(0.5));  // 50%
        System.out.println(df.format(-0.5)); // menos 50 puff

        df = new DecimalFormat("#.00%");
        System.out.println(df.parse("1,11%")); // 0.0111
        System.out.println(df.parse("1,1%")); // 0.011000000000000001
//        System.out.println(df.parse("1.11")); // Error, falta el %

        df = new DecimalFormat("El número # es primo");
        System.out.println(df.parse("El número 13 es primo")); // 13
//        System.out.println(df.parse("13 es primo")); // Error, falta parte del texto

    }
}
