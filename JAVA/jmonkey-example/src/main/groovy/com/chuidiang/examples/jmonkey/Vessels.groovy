package com.chuidiang.examples.jmonkey;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioListenerState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class Vessels extends SimpleApplication {
    private Node pivot;


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

        Vessel vessel = new Vessel(assetManager, new VesselData(
                name:"Federica",
                high: 1f,
                length: 10f,
                beam: 1f,
                heading: new Vector3f(10,0,-10),
                speed: 1
        ));
        rootNode.attachChild(vessel);

        Sea s = new Sea(assetManager);
        rootNode.attachChild(s);

        cam.setLocation(new Vector3f(10f,10f,100f));
        getFlyByCamera().setZoomSpeed(10f);

        // Uncomment to save a video in your home dir.
//        stateManager.attach(new VideoRecorderAppState());
    }

    @Override
    public void simpleUpdate(float tpf) {
        rootNode.getChild("Federica").move(new Vector3f(tpf,0f,tpf));
    }
}
