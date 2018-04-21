package com.chuidiang.examples.weka;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Genera barcos para entrenamiento.
 * Barcos normales tienen velocidad media, son grandes y tienen mmsi
 * Lanchas son pequeñas, rapidas y sin mmsi
 * Pateras son pequeñas, lentas y con mmsi.
 */
public class GenerateTestVessels {
    public static String[] types = {"patera","lancha","barco"};
    public static Instances getData() throws Exception {
        ArrayList<Attribute> atts = new ArrayList<>();
        Instances       data;
        double[]        vals;


        // Atributos, diciendo nombre y posibles valores. Si no se indica
        // nada, son numéricos.
        atts.add(new Attribute("type", Arrays.asList(types)));
        atts.add(new Attribute("size"));
        atts.add(new Attribute("vmax"));
        atts.add(new Attribute("vmin"));
        atts.add(new Attribute("mmsi", Arrays.asList("si","no")));

        // Creamos el conjunto de datos, de momento vacío, indicando qué atributos
        // van a llevar.
        data = new Instances("MyData", atts, 0);

        // Rellenamos datos.
        // pateras
        for (int i=0;i<10;i++) {
            vals = getPatera(data.numAttributes());
            data.add(new DenseInstance(1.0,vals));
        }

        // lanchas
        for (int i=0;i<10;i++) {
            vals = getLancha(data.numAttributes());
            data.add(new DenseInstance(1.0,vals));
        }

        // barco
        for (int i=0;i<10;i++) {
            vals = getBarco(data.numAttributes());
            data.add(new DenseInstance(1.0,vals));
        }

        data.setClassIndex(0);
        // 4. output data
//        System.out.println(data);

        return data;
    }

    public static double[] getBarco(int numAttributes) {
        double[] vals;
        vals = new double[numAttributes];
        vals[0]=2; // barco
        vals[1]=Math.random()*30+50; // grande
        vals[2]=Math.random()*5+25; // media
        vals[3]=Math.random()*2+5; // rapida
        vals[4]=0; // con mmsi
        return vals;
    }

    public static double[] getPatera(int numAttributes) {
        double[] vals;
        vals = new double[numAttributes];
        vals[0]=0; // patera
        vals[1]=Math.random()*10+10; // pequeña
        vals[2]=Math.random()*2+10; // lento
        vals[3]=Math.random()*2+5; // lento
        vals[4]=1; // sin mmsi
        return vals;
    }

    public static double[] getLancha(int numAttributes) {
        double[] vals;
        vals = new double[numAttributes];
        vals[0]=1; // lancha
        vals[1]=Math.random()*10+10; // pequeña
        vals[2]=Math.random()*25+25; // rapida
        vals[3]=Math.random()*2+5; // rapida
        vals[4]=1; // sin mmsi
        return vals;
    }
}
