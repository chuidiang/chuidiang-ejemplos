package com.chuidiang.examples.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;

public class Vessel extends Node {
    public Vessel(String name, AssetManager assetManager, float length, Vector3f direction){
        super (name);
        Box vessel = new Box(length, 1f,1f);

        Geometry geom = new Geometry("Box", vessel);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
//        mat.setColor("Color", ColorRGBA.Red);   // set color of material to blue
        mat.setTexture("ColorMap", assetManager.loadTexture("silver_metal_sign_textured_T.png"));
        geom.setMaterial(mat);                   // set the cube's material
        geom.lookAt(direction,new Vector3f(0,1,0));
        attachChild(geom);


        Cylinder chim = new Cylinder(100,10,.5f, 2f);
        geom = new Geometry("chim",chim);
        geom.setMaterial(mat);

        geom.lookAt(new Vector3f(0,10,0), new Vector3f(1,0,0));
        geom.move(new Vector3f(0, 2,0));
        attachChild(geom);
    }
}
