package com.chuidiang.examples.weka;


import weka.clusterers.EM;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class Cluster {
    public static void main(String[] args) throws Exception {
        Instances data = GenerateTestVessels.getData();
        data.setClassIndex(-1); // No class index.

        Remove rm = new Remove();
        rm.setAttributeIndices("1");
        rm.setInputFormat(data);
        data = Filter.useFilter(data,rm);
        System.out.println(data);


        EM cw = new EM();

        cw.buildClusterer(data);
        System.out.println(cw);

        System.out.println(cw.clusterInstance(data.firstInstance()));
    }
}
