package com.chuidiang.examples.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;

public class Vessel extends Node {
    public Vessel(AssetManager assetManager, VesselData data){
        super (data.getName());
        Box vessel = new Box(data.getLength(), data.getBeam(),data.getHigh());

        Geometry geom = new Geometry(data.getName(), vessel);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
//        mat.setColor("Color", ColorRGBA.Red);   // set color of material to blue
        mat.setTexture("ColorMap", assetManager.loadTexture("silver_metal_sign_textured_T.png"));
        geom.setMaterial(mat);                   // set the cube's material
        geom.lookAt(data.getHeading(),Vector3f.UNIT_Y);
        attachChild(geom);


        Cylinder smokestack = new Cylinder(5,5,data.getBeam()/2f, data.getHigh()*2f);
        geom = new Geometry("smokestack",smokestack);
        geom.setMaterial(mat);

        geom.lookAt(new Vector3f(0,100,0), Vector3f.UNIT_X);
        geom.move(new Vector3f(0, data.getHigh()*2f,0));
        attachChild(geom);
    }
}
