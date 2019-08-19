package com.chuidiang.examples.luciad.gxy;

import com.luciad.format.raster.TLcdGeoTIFFModelDecoder;
import com.luciad.geodesy.TLcdGeodeticDatum;
import com.luciad.model.ILcdModel;
import com.luciad.projection.TLcdMercator;
import com.luciad.reference.TLcdGridReference;
import com.luciad.view.TLcdAWTEventFilterBuilder;
import com.luciad.view.gxy.ILcdGXYLayer;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.gxy.asynchronous.TLcdGXYAsynchronousLayerWrapper;
import com.luciad.view.gxy.asynchronous.manager.TLcdGXYAsynchronousPaintQueueManager;
import com.luciad.view.gxy.controller.TLcdGXYCompositeController;
import com.luciad.view.gxy.controller.TLcdGXYPanController;
import com.luciad.view.gxy.controller.TLcdGXYZoomWheelController;
import com.luciad.view.map.TLcdMapJPanel;
import com.luciad.view.swing.TLcdLayerTree;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GXYMainExample {



    public static void main(String[] args) throws IOException {
        new GXYMainExample();
    }

    public GXYMainExample() throws IOException {
        ILcdModel model = createModel();
        TLcdMapJPanel aView =create2DView();

        // Creates the background layer.
        ILcdGXYLayer layer = new TLcdGXYAsynchronousLayerWrapper(new ImageLayerFactory().
                createGXYLayer(model));

        // Adds the background layer to the view and moves the grid layer to the top.
        aView.addGXYLayer(layer);
        aView.moveLayerAt(aView.layerCount() - 1, aView.getGridLayer());

        addControllers(aView);

        JFrame fFrame = new JFrame("LuciadLightspeed GXY Fundamentals");
        fFrame.getContentPane().setLayout(new BorderLayout());
        fFrame.getContentPane().add(aView, BorderLayout.CENTER);
        fFrame.add(new JScrollPane(new TLcdLayerTree(aView)), BorderLayout.EAST);
        fFrame.setSize(800, 600);
        fFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fFrame.setVisible(true);
    }

    private void addControllers(ILcdGXYView aView) {
        TLcdGXYCompositeController compositeController = new TLcdGXYCompositeController();
        TLcdGXYPanController controller = new TLcdGXYPanController();
        controller.setAWTFilter(TLcdAWTEventFilterBuilder.newBuilder().leftMouseButton().build());
        controller.setDragViewOnPan(true);
        compositeController.addGXYController(controller);
        compositeController.addGXYController(new TLcdGXYZoomWheelController());
        aView.setGXYController(compositeController);
    }

    private TLcdMapJPanel create2DView() {
        // Creates the 2D view.
        TLcdMapJPanel map = new TLcdMapJPanel();
        TLcdGXYAsynchronousPaintQueueManager manager = new TLcdGXYAsynchronousPaintQueueManager();
        manager.setGXYView(map);

        // Defines a custom world reference for the map.
        map.setXYWorldReference(
                new TLcdGridReference( new TLcdGeodeticDatum( ), new TLcdMercator() )
        );
        return map;
    }

    private ILcdModel createModel() throws IOException {
        // Creates the model.
        TLcdGeoTIFFModelDecoder modelDecoder = new TLcdGeoTIFFModelDecoder();
        ILcdModel model = modelDecoder.decode("src/main/files/Data/GeoTIFF/BlueMarble/bluemarble.tif");
        return model;
    }
}
