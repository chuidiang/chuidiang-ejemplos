package com.chuidiang.examples.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Line;

public class Axis extends Node {
    public Axis(AssetManager assetManager){
        super("axis");
        Line xAxis = new Line(Vector3f.ZERO, new Vector3f(1000.0f, 0.0f, 0.0f));
        Geometry xGeometry = new Geometry("xAxis", xAxis);
        Material xMat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        xMat.setColor("Color", ColorRGBA.Blue);
        xGeometry.setMaterial(xMat);

        Line yAxis = new Line(Vector3f.ZERO, new Vector3f(.0f, 1000.0f, 0.0f));
        Geometry yGeometry = new Geometry("yAxis", yAxis);
        Material yMat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        yMat.setColor("Color", ColorRGBA.Cyan);
        yGeometry.setMaterial(yMat);

        Line zAxis = new Line(Vector3f.ZERO, new Vector3f(.0f, .0f, 1000.0f));
        Geometry zGeometry = new Geometry("zAxis", zAxis);
        Material zMat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        zMat.setColor("Color", ColorRGBA.Green);
        zGeometry.setMaterial(zMat);


        attachChild(xGeometry);
        attachChild(yGeometry);
        attachChild(zGeometry);

    }


}
