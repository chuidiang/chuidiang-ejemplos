package com.chuidiang.examples.weka;

import weka.core.*;

import java.util.ArrayList;

/**
 * Ejemplo copiado de internet, para ver esto del fichero
 */
public class GenerateArffFile {
    public static void main(String[] args) throws Exception {
        ArrayList<Attribute> atts;
        ArrayList<Attribute>      attsRel;
        ArrayList<String>      attVals;
        ArrayList<String>      attValsRel;
        Instances       data;
        Instances       dataRel;
        double[]        vals;
        double[]        valsRel;
        int             i;

        // 1. set up attributes
        atts = new ArrayList<>();
        // - numeric
        atts.add(new Attribute("att1"));
        // - nominal
        attVals = new ArrayList<>();
        for (i = 0; i < 5; i++)
            attVals.add("val" + (i+1));
        atts.add(new Attribute("att2", attVals));
        // - string
        atts.add(new Attribute("att3", (ArrayList) null));
        // - date
        atts.add(new Attribute("att4", "yyyy-MM-dd"));
        // - relational
        attsRel = new ArrayList<>();
        // -- numeric
        attsRel.add(new Attribute("att5.1"));
        // -- nominal
        attValsRel = new ArrayList<>();
        for (i = 0; i < 5; i++)
            attValsRel.add("val5." + (i+1));
        attsRel.add(new Attribute("att5.2", attValsRel));
        dataRel = new Instances("att5", attsRel, 0);
        atts.add(new Attribute("att5", dataRel, 0));

        // 2. create Instances object
        data = new Instances("MyRelation", atts, 0);

        // 3. fill with data
        // first instance
        vals = new double[data.numAttributes()];
        // - numeric
        vals[0] = Math.PI;
        // - nominal
        vals[1] = attVals.indexOf("val3");
        // - string
        vals[2] = data.attribute(2).addStringValue("This is a string!");
        // - date
        vals[3] = data.attribute(3).parseDate("2001-11-09");
        // - relational
        dataRel = new Instances(data.attribute(4).relation(), 0);
        // -- first instance
        valsRel = new double[2];
        valsRel[0] = Math.PI + 1;
        valsRel[1] = attValsRel.indexOf("val5.3");
        dataRel.add(new DenseInstance(1.0, valsRel));
        // -- second instance
        valsRel = new double[2];
        valsRel[0] = Math.PI + 2;
        valsRel[1] = attValsRel.indexOf("val5.2");
        dataRel.add(new DenseInstance(1.0, valsRel));
        vals[4] = data.attribute(4).addRelation(dataRel);
        // add
        data.add(new DenseInstance(1.0, vals));

        // second instance
        vals = new double[data.numAttributes()];  // important: needs NEW array!
        // - numeric
        vals[0] = Math.E;
        // - nominal
        vals[1] = attVals.indexOf("val1");
        // - string
        vals[2] = data.attribute(2).addStringValue("And another one!");
        // - date
        vals[3] = data.attribute(3).parseDate("2000-12-01");
        // - relational
        dataRel = new Instances(data.attribute(4).relation(), 0);
        // -- first instance
        valsRel = new double[2];
        valsRel[0] = Math.E + 1;
        valsRel[1] = attValsRel.indexOf("val5.4");
        dataRel.add(new DenseInstance(1.0, valsRel));
        // -- second instance
        valsRel = new double[2];
        valsRel[0] = Math.E + 2;
        valsRel[1] = attValsRel.indexOf("val5.1");
        dataRel.add(new DenseInstance(1.0, valsRel));
        vals[4] = data.attribute(4).addRelation(dataRel);
        // add
        data.add(new DenseInstance(1.0, vals));

        // 4. output data
        System.out.println(data);


    }
}
