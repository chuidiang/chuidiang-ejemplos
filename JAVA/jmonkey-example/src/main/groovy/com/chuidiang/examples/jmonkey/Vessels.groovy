package com.chuidiang.examples.jmonkey

import com.jme3.app.DebugKeysAppState
import com.jme3.app.FlyCamAppState
import com.jme3.app.SimpleApplication
import com.jme3.audio.AudioListenerState
import com.jme3.math.Vector3f

public class Vessels extends SimpleApplication {
    def vessels = new ArrayList<>();

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


        /** Create a pivot node at (0,0,0) and attach it to the root node */
        Axis axis = new Axis(assetManager);
        rootNode.attachChild(axis); // put this node in the scene

        VesselGenerator.data.each {data ->
            Vessel vessel = new Vessel(assetManager, data);
            vessels.add(vessel)
            rootNode.attachChild(vessel);
        }

//        Spatial strangeBall = assetManager.loadModel("essex_scb-125_generic.obj");
//        Material mat_default = new Material(
//                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        Material mat_default = new Material(
//                assetManager, "aircraft/aircraft.mtl");
//        mat_default.setTexture("ColorMap", getAssetManager().loadTexture("barco/Metal_Aluminum_Anodized.png"))
//        strangeBall.setMaterial(mat_default);
//        rootNode.attachChild(strangeBall);

        Sea s = new Sea(assetManager);
        rootNode.attachChild(s);

        cam.setLocation(new Vector3f(10f,10f,100f));
        getFlyByCamera().setZoomSpeed(10f);

//        DirectionalLight sun = new DirectionalLight();
//        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
//        rootNode.addLight(sun);

        // Uncomment to save a video in your home dir.
//        stateManager.attach(new VideoRecorderAppState());
    }

    @Override
    public void simpleUpdate(float tpf) {
        vessels.each {Vessel vessel ->
            vessel.move(tpf);
        }
    }
}
