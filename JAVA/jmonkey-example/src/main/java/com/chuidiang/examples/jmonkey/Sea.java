package com.chuidiang.examples.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;


public class Sea extends Node {
    public Sea(AssetManager assetManager){
        Box b = new Box(1000,0,1000);
        Geometry g = new Geometry("sea", b);
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
//        mat.setColor("Color", ColorRGBA.Red);   // set color of material to blue
        mat.setTexture("ColorMap", assetManager.loadTexture("Elemento_Rayo_Ola_de_Inspiración_2.png"));
        g.setMaterial(mat);                   // set the cube's material
        attachChild(g);

    }
}
