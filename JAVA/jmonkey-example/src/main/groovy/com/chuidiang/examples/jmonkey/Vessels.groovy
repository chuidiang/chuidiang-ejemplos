package com.chuidiang.examples.jmonkey

import com.chuidiang.examples.jmonkey.snapshot.IfzImageListener
import com.chuidiang.examples.jmonkey.snapshot.IfzSnapshotAction
import com.chuidiang.examples.jmonkey.snapshot.SnapshotWindow
import com.jme3.app.DebugKeysAppState
import com.jme3.app.FlyCamAppState
import com.jme3.app.SimpleApplication
import com.jme3.audio.AudioListenerState
import com.jme3.math.Vector3f
import com.jme3.scene.Node
import com.jme3.texture.Image
import com.jme3.util.BufferUtils
import com.jme3.util.Screenshots
import com.jme3.util.SkyFactory

import java.awt.image.BufferedImage
import java.nio.ByteBuffer

public class Vessels extends SimpleApplication {
    def vessels = new ArrayList<>();
    private Vector3f lightPos = new Vector3f(33, 12, -29)
    private SnapshotWindow snapshot = null;


    public Vessels() {
        super(new FlyCamAppState(), new AudioListenerState(), new DebugKeysAppState());

    }

    public static void main(String[] args) {
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

        VesselGenerator.data.each { data ->
            Vessel vessel = new Vessel(assetManager, data);
            vessels.add(vessel)
            sceneNode.attachChild(vessel);
        }

        cam.setLocation(new Vector3f(10f, 10f, 100f));
        getFlyByCamera().setZoomSpeed(10f);

        sceneNode.attachChild(new Seabed(getAssetManager()));

        sceneNode.attachChild(
                SkyFactory.createSky(getAssetManager(), "sky.jpg", SkyFactory.EnvMapType.SphereMap));

        rootNode.attachChild(new Sea(assetManager, sceneNode, getViewPort()));


        rootNode.addLight(new Sun());

        rootNode.attachChild(sceneNode);


        snapshot = new SnapshotWindow(new IfzSnapshotAction() {
            @Override
            void takeSnapshot(IfzImageListener listener) {
                imageListener = listener
            }
        });
    }


    private IfzImageListener imageListener=null;

    @Override
    public void simpleUpdate(float tpf) {
        vessels.each { Vessel vessel ->
            vessel.move(tpf);
        }

        if (null !=imageListener) {
            imageListener.takeImage(takeSnapshot());
            imageListener=null;
        }
    }

    public BufferedImage takeSnapshot() {
        int width = viewPort.getCamera().getWidth();
        int height = viewPort.getCamera().getHeight();
        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_4BYTE_ABGR);

        renderManager.getRenderer().readFrameBufferWithFormat(viewPort.getOutputFrameBuffer(), buffer, Image.Format.BGRA8);
        Screenshots.convertScreenShot(buffer, image);

        return image;

    }
}