package com.chuidiang.examples.weka;

import weka.clusterers.Cobweb;
import weka.clusterers.EM;
import weka.core.Instance;
import weka.core.Instances;

public class Cluster {
    public static void main(String[] args) throws Exception {
        Instances data = GenerateTestVessels.getData();
        data.setClassIndex(-1); // No class index.

        EM cw = new EM();
        cw.buildClusterer(data);
//        Instance current;
//        while ((current = loader.getNextInstance(structure)) != null)
//            cw.updateClusterer(current);
//        cw.updateFinished();
        System.out.println(cw);
    }
}
