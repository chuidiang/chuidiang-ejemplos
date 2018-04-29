package com.chuidiang.examples.jmonkey

import com.jme3.app.DebugKeysAppState
import com.jme3.app.FlyCamAppState
import com.jme3.app.SimpleApplication
import com.jme3.app.state.VideoRecorderAppState
import com.jme3.audio.AudioListenerState
import com.jme3.light.DirectionalLight
import com.jme3.material.Material
import com.jme3.math.*
import com.jme3.renderer.queue.RenderQueue
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.shape.Quad
import com.jme3.util.SkyFactory
import com.jme3.water.SimpleWaterProcessor

public class Vessels extends SimpleApplication {
    def vessels = new ArrayList<>();
    private Vector3f lightPos =  new Vector3f(33,12,-29);
    public Vessels(){
        super(new FlyCamAppState(), new AudioListenerState(), new DebugKeysAppState());

    }

    public static void main(String[] args){
        Vessels app = new Vessels();
        app.setShowSettings(false);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Node sceneNode = new Node("scene");

        /** Create a pivot node at (0,0,0) and attach it to the root node */
        Axis axis = new Axis(assetManager);
        rootNode.attachChild(axis); // put this node in the scene

        VesselGenerator.data.each {data ->
            Vessel vessel = new Vessel(assetManager, data);
            vessels.add(vessel)
            sceneNode.attachChild(vessel);
        }

//        Spatial strangeBall = assetManager.loadModel("essex_scb-125_generic.obj");
//        Material mat_default = new Material(
//                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        Material mat_default = new Material(
//                assetManager, "aircraft/aircraft.mtl");
//        mat_default.setTexture("ColorMap", getAssetManager().loadTexture("barco/Metal_Aluminum_Anodized.png"))
//        strangeBall.setMaterial(mat_default);
//        rootNode.attachChild(strangeBall);

//        Sea s = new Sea(assetManager);
//        rootNode.attachChild(s);

        cam.setLocation(new Vector3f(10f,10f,100f));
        getFlyByCamera().setZoomSpeed(10f);




// we create a water processor
        SimpleWaterProcessor waterProcessor = new SimpleWaterProcessor(assetManager);
        waterProcessor.setReflectionScene(sceneNode);

// we set the water plane
        Vector3f waterLocation=new Vector3f(0,0,0);
        waterProcessor.setPlane(new Plane(Vector3f.UNIT_Y, waterLocation.dot(Vector3f.UNIT_Y)));
        viewPort.addProcessor(waterProcessor);

// we set wave properties
        waterProcessor.setWaterDepth(4);         // transparency of water
        waterProcessor.setDistortionScale(0.15f); // strength of waves
        waterProcessor.setWaveSpeed(0.05f);       // speed of waves



        Quad deep = new Quad(2000,2000);
        Geometry deepGeometry = new Geometry("deep", deep);
        Material deepMaterial = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md" );
        deepMaterial.setColor("Color",new ColorRGBA(0,0.3,0.3,1));
        deepGeometry.setMaterial(deepMaterial);
        deepGeometry.setLocalRotation(new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X));
        deepGeometry.setLocalTranslation(-1000,-10,1000);
        sceneNode.attachChild(deepGeometry);

// we define the wave size by setting the size of the texture coordinates
        Quad quad = new Quad(2000,2000);
        quad.scaleTextureCoordinates(new Vector2f(6f,6f));

// we create the water geometry from the quad
        Geometry water=new Geometry("water", quad);
        water.setLocalRotation(new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X));
        water.setLocalTranslation(-1000, 0, 1000);
        water.setShadowMode(RenderQueue.ShadowMode.Receive);
        water.setMaterial(waterProcessor.getMaterial());
        rootNode.attachChild(water);


        sceneNode.attachChild(
                SkyFactory.createSky(getAssetManager(), "sky.jpg", SkyFactory.EnvMapType.SphereMap));


        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        rootNode.addLight(sun);

        rootNode.attachChild(sceneNode);
        // Uncomment to save a video in your home dir.
        stateManager.attach(new VideoRecorderAppState());
    }

    @Override
    public void simpleUpdate(float tpf) {
        vessels.each {Vessel vessel ->
            vessel.move(tpf);
        }
    }
}
