package com.chuidiang.examples.luciad.business_data.gxy;

import com.chuidiang.examples.luciad.business_data.FlightPlanDataTypes;
import com.chuidiang.examples.luciad.business_data.Util;
import com.chuidiang.examples.luciad.gxy.ImageLayerFactory;
import com.luciad.format.raster.TLcdGeoTIFFModelDecoder;
import com.luciad.geodesy.TLcdGeodeticDatum;
import com.luciad.model.ILcdModel;
import com.luciad.model.TLcd2DBoundsIndexedModel;
import com.luciad.model.TLcdDataModelDescriptor;
import com.luciad.projection.TLcdMercator;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.reference.TLcdGridReference;
import com.luciad.view.TLcdAWTEventFilterBuilder;
import com.luciad.view.gxy.ILcdGXYLayer;
import com.luciad.view.gxy.asynchronous.TLcdGXYAsynchronousLayerWrapper;
import com.luciad.view.gxy.asynchronous.manager.TLcdGXYAsynchronousPaintQueueManager;
import com.luciad.view.gxy.controller.TLcdGXYCompositeController;
import com.luciad.view.gxy.controller.TLcdGXYEditController2;
import com.luciad.view.gxy.controller.TLcdGXYPanController;
import com.luciad.view.gxy.controller.TLcdGXYZoomWheelController;
import com.luciad.view.map.TLcdMapJPanel;
import com.luciad.view.swing.TLcdLayerTree;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;

public class GxyBusinessDataMainExample {
    public static void main(String[] args) throws IOException {
        new GxyBusinessDataMainExample();
    }

    public GxyBusinessDataMainExample() throws IOException {
        TLcd2DBoundsIndexedModel model = new TLcd2DBoundsIndexedModel();
        model.setModelReference(new TLcdGeodeticReference());

        TLcdDataModelDescriptor dataModelDescriptor=new TLcdDataModelDescriptor(
                "sourceName",
                "typeName",
                "the layer name",
                FlightPlanDataTypes.getDataModel(),
                Collections.singleton(FlightPlanDataTypes.FLIGHT_PLAN_DATA_TYPE),
                FlightPlanDataTypes.getDataModel().getTypes()
        );
        model.setModelDescriptor(dataModelDescriptor);

        Util.addData(model);
        Util.addCircles(model);
        Util.addZones(model);

        TLcdMapJPanel map = new TLcdMapJPanel();
        TLcdGXYAsynchronousPaintQueueManager manager = new TLcdGXYAsynchronousPaintQueueManager();
        manager.setGXYView(map);

        // Defines a custom world reference for the map.
        map.setXYWorldReference(
                new TLcdGridReference( new TLcdGeodeticDatum( ), new TLcdMercator() )
        );

        ILcdGXYLayer layer = new TLcdGXYAsynchronousLayerWrapper(new ImageLayerFactory().
                createGXYLayer(createModel()));

        // Adds the background layer to the view and moves the grid layer to the top.
        map.setGXYLayerFactory(
                new GxyFlightPlanLayerFactory());



        TLcdGXYCompositeController compositeController = new TLcdGXYCompositeController();
        TLcdGXYPanController controller = new TLcdGXYPanController();
        controller.setAWTFilter(TLcdAWTEventFilterBuilder.newBuilder().middleMouseButton().build());
        controller.setDragViewOnPan(true);
        compositeController.addGXYController(controller);
        compositeController.addGXYController(new TLcdGXYZoomWheelController());
        compositeController.addGXYController(new TLcdGXYEditController2());
        map.setGXYController(compositeController);
        map.addGXYLayer(layer);
        map.addModel(model);
        map.moveLayerAt(map.layerCount()-1,map.getGridLayer());


        JFrame fFrame = new JFrame("LuciadLightspeed gxy Business Data");
        fFrame.getContentPane().setLayout(new BorderLayout());
        fFrame.getContentPane().add(map, BorderLayout.CENTER);
        fFrame.add(new JScrollPane(new TLcdLayerTree(map)), BorderLayout.EAST);
        fFrame.setSize(800, 600);
        fFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fFrame.setVisible(true);
    }

    private ILcdModel createModel() throws IOException {
        // Creates the model.
        TLcdGeoTIFFModelDecoder modelDecoder = new TLcdGeoTIFFModelDecoder();
        ILcdModel model = modelDecoder.decode("src/main/files/Data/GeoTIFF/BlueMarble/bluemarble.tif");
        return model;
    }
}
