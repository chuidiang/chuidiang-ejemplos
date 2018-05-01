package com.chuidiang.examples.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.math.*;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.water.SimpleWaterProcessor;


public class Sea extends Geometry {
    public Sea(AssetManager assetManager, Node sceneNode, ViewPort viewPort) {
        super("water");

        // we create a water processor
        SimpleWaterProcessor waterProcessor = new SimpleWaterProcessor(assetManager);
        waterProcessor.setReflectionScene(sceneNode);

        // we set the water plane
        Vector3f waterLocation = new Vector3f(0, 0, 0);
        waterProcessor.setPlane(new Plane(Vector3f.UNIT_Y, waterLocation.dot(Vector3f.UNIT_Y)));
        viewPort.addProcessor(waterProcessor);

        // we set wave properties
        waterProcessor.setWaterDepth(4);         // transparency of water
        waterProcessor.setDistortionScale(0.15f); // strength of waves
        waterProcessor.setWaveSpeed(0.05f);       // speed of waves

        // we define the wave size by setting the size of the texture coordinates
        Quad quad = new Quad(2000, 2000);
        quad.scaleTextureCoordinates(new Vector2f(6f, 6f));

        // we create the water geometry from the quad
        setMesh(quad);
        setLocalRotation(new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X));
        setLocalTranslation(-1000, 0, 1000);
        setShadowMode(RenderQueue.ShadowMode.Receive);
        setMaterial(waterProcessor.getMaterial());
    }
}
