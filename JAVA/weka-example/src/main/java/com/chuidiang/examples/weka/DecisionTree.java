package com.chuidiang.examples.weka;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.gui.treevisualizer.TreeVisualizer;

public class DecisionTree {
    public static void main(String[] args) throws Exception {
        Classifier j48 = new J48();
        Instances trainingData = GenerateTestVessels.getData();
        j48.buildClassifier(trainingData);
        System.out.println(j48);



        double[] vesselUnderTest = GenerateTestVessels.getBarco(5);
        vesselUnderTest[0]=0.0;

        DenseInstance inst = new DenseInstance(1.0,vesselUnderTest);
        inst.setDataset(trainingData);
        inst.setClassMissing();
        System.out.println(inst);

        double result = j48.classifyInstance(inst);
        System.out.println(GenerateTestVessels.types[(int)result]);
    }
}
