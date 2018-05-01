package com.chuidiang.examples.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;

public class Seabed extends Geometry {
    public Seabed (AssetManager assetManager){
            super("seabed",new Quad(2000, 2000));


            Material deepMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            deepMaterial.setColor("Color", new ColorRGBA(0, 0.3f, 0.3f, 1));
            setMaterial(deepMaterial);
            setLocalRotation(new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X));
            setLocalTranslation(-1000, -10, 1000);

    }
}
