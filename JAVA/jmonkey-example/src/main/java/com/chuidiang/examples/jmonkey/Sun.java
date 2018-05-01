package com.chuidiang.examples.jmonkey;

import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;

public class Sun extends DirectionalLight {
    public Sun(){
        setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
    }
}
