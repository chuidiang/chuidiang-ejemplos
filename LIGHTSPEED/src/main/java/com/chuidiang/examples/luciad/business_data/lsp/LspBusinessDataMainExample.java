package com.chuidiang.examples.luciad.business_data.lsp;

import com.chuidiang.examples.luciad.business_data.FlightPlanDataTypes;
import com.chuidiang.examples.luciad.business_data.Util;
import com.chuidiang.examples.luciad.lsp.BasicLayerFactory;
import com.luciad.earth.model.TLcdEarthRepositoryModelDecoder;
import com.luciad.format.shp.TLcdSHPModelDecoder;
import com.luciad.geodesy.TLcdGeodeticDatum;
import com.luciad.imaging.ALcdBasicImage;
import com.luciad.imaging.TLcdImageBuilder;
import com.luciad.imaging.TLcdImageModelDescriptor;
import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelDecoder;
import com.luciad.model.TLcd2DBoundsIndexedModel;
import com.luciad.model.TLcdDataModelDescriptor;
import com.luciad.projection.TLcdEquidistantCylindrical;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.reference.TLcdGridReference;
import com.luciad.shape.shape3D.TLcdLonLatHeightBounds;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.shape.shape3D.TLcdXYZPoint;
import com.luciad.transformation.TLcdDefaultModelXYZWorldTransformation;
import com.luciad.util.ILcdSelectionListener;
import com.luciad.util.TLcdOutOfBoundsException;
import com.luciad.util.TLcdSelectionChangedEvent;
import com.luciad.view.TLcdDomainObjectContext;
import com.luciad.view.lightspeed.ILspAWTView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspViewBuilder;
import com.luciad.view.lightspeed.camera.ALspViewXYZWorldTransformation;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspCompositeLayerFactory;
import com.luciad.view.lightspeed.swing.TLspBalloonManager;
import com.luciad.view.lightspeed.util.TLspViewTransformationUtil;
import com.luciad.view.swing.TLcdLayerTree;
import com.luciad.view.swing.TLcdModelElementBalloonDescriptor;
import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class LspBusinessDataMainExample {
    public static void main(String[] args) throws IOException {
        new LspBusinessDataMainExample();
    }

    public LspBusinessDataMainExample() throws IOException {
        TLcd2DBoundsIndexedModel model = new TLcd2DBoundsIndexedModel();
        model.setModelReference(new TLcdGeodeticReference());

        TLcdDataModelDescriptor dataModelDescriptor=new TLcdDataModelDescriptor(
                "sourceName",
                "typeName",
                "displayName",
                FlightPlanDataTypes.getDataModel(),
                Collections.singleton(FlightPlanDataTypes.FLIGHT_PLAN_DATA_TYPE),
                FlightPlanDataTypes.getDataModel().getTypes()
        );
        model.setModelDescriptor(dataModelDescriptor);

        Util.addData(model);
        Util.addCircles(model);
        Util.addZones(model);


        ILspAWTView aView = TLspViewBuilder.newBuilder().buildAWTView();

        // Adds the background layer to the view and moves the grid layer to the top.
        aView.setLayerFactory(new TLspCompositeLayerFactory(
                new VesselLayerFactory(),
                new LspFlightPlanLayerFactory(), new BasicLayerFactory(),
                new ImageLayerFactory()));


        createShapefileModelAndAddLayers(aView);
        createTileModelAndAddLayers(aView);
        aView.addLayersFor(model);
        aView.addLayersFor(new ImageModel());

        MyModel vesselModel=new MyModel();
//        VesselEngine movingVessels = new VesselEngine(vesselModel);
        aView.addLayersFor(vesselModel);
//        aView.setAutoUpdate(true);

        addBalloon(aView, vesselModel);

        aView.getHostComponent().addMouseMotionListener(new MouseAdapter() {


            @Override
            public void mouseMoved(MouseEvent e) {

                TLcdXYZPoint point =new TLcdXYZPoint();

                try {
                    aView.getViewXYZWorldTransformation().viewAWTPoint2worldSFCT(
                            e.getPoint(),
                            ALspViewXYZWorldTransformation.LocationMode.CLOSEST_SURFACE,
                            point);
                    TLcdDefaultModelXYZWorldTransformation transformation=new TLcdDefaultModelXYZWorldTransformation(
                            vesselModel.getModelReference(),
                            aView.getXYZWorldReference());
                    TLcdLonLatHeightPoint lonLatHeightPoint = new TLcdLonLatHeightPoint();
                    transformation.worldPoint2modelSFCT(point,lonLatHeightPoint);
                    System.out.println(lonLatHeightPoint);
                } catch (TLcdOutOfBoundsException e1) {
                    e1.printStackTrace();
                }
            }
        });





        JFrame fFrame = new JFrame("LuciadLightspeed AWT Business Data");
        fFrame.getContentPane().setLayout(new BorderLayout());
        fFrame.getContentPane().add(aView.getHostComponent(), BorderLayout.CENTER);
        fFrame.add(new JScrollPane(new TLcdLayerTree(aView)), BorderLayout.EAST);
        fFrame.add(createToolBar(aView), BorderLayout.NORTH);
        fFrame.setSize(800, 600);
        fFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fFrame.setVisible(true);


//        movingVessels.start();
    }

    private void addBalloon(ILspAWTView aView, MyModel vesselModel) {
        TLspBalloonManager balloonManager = new TLspBalloonManager(aView);
        balloonManager.setBalloonContentProvider(new MyBalloonProvider());
//        TLinBalloonViewSelectionListener listener = new TLinBalloonViewSelectionListener(aView,balloonManager);
//        aView.addLayerSelectionListener(listener);

        ILcdSelectionListener listener = new ILcdSelectionListener() {
            @Override
            public void selectionChanged(TLcdSelectionChangedEvent tLcdSelectionChangedEvent) {
                Collection collection = tLcdSelectionChangedEvent.getChangedObjects();
                if (!collection.isEmpty()) {
                    Iterator iterator = collection.iterator();
//                    VesselPoint vesselPoint = (VesselPoint)iterator.next();
                    balloonManager.setBalloonDescriptor(
                        new TLcdModelElementBalloonDescriptor(new TLcdDomainObjectContext(
                                iterator.next(),vesselModel,aView.layerOf(vesselModel),aView)
                        )
                    );
                }
            }
        };
        aView.addLayerSelectionListener(listener);


    }

    private void createTileModelAndAddLayers(ILspAWTView aView) throws IOException {
        // Create a TLcdEarthRepositoryModelDecoder to decode Luciad Earth repositories
        ILcdModelDecoder earthDecoder = new TLcdEarthRepositoryModelDecoder();
        // Decode a sample data set (imagery data)
        ILcdModel earthModel = earthDecoder.decode("src/main/files/Data/Earth/SanFrancisco/tilerepository.cfg");
        // Calling addLayersFor() will cause the view to invoke its layer factory with
        // the given model and then add the resulting layers to itself
        aView.addLayersFor(earthModel);
    }

    private void createShapefileModelAndAddLayers(ILspAWTView aView) throws IOException {
        // TLcdSHPModelDecoder can read ESRI SHP files
        ILcdModelDecoder decoder = new TLcdSHPModelDecoder();
        // Decode world.shp to create an ILcdModel
        ILcdModel shpModel = decoder.decode("src/main/files/Data/Shp/World/world.shp");
        // Calling addLayers() will cause the view to invoke its layer factory with
        // the given model and then add the resulting layers to itself
        Collection<ILspLayer> shpLayer = aView.addLayersFor(shpModel);
    }

    private  JToolBar createToolBar(ILspView aView){
        // Create and add toolbar to frame
        JToolBar toolBar = new JToolBar();
        // Create a button group for the radio buttons
        ButtonGroup group = new ButtonGroup();
        // Create a button to switch to 2D
        JRadioButton b2d = new JRadioButton("2D", true);
        b2d.setAction(new AbstractAction("2D") {
            @Override
            public void actionPerformed(ActionEvent e) {
                TLspViewTransformationUtil.setup2DView(
                        aView,
                        new TLcdGridReference(new TLcdGeodeticDatum(),
                                new TLcdEquidistantCylindrical()),
                        true
                );
            }
        });
        b2d.setToolTipText("Switch the view to 2D");
        group.add(b2d);
        // Create a button to switch to 3D
        JRadioButton b3d = new JRadioButton("3D", false);
        b3d.setAction(new AbstractAction("3D") {
            @Override
            public void actionPerformed(ActionEvent e) {
                TLspViewTransformationUtil.setup3DView(aView, true);
            }
        });
        b3d.setToolTipText("Switch the view to 3D");
        group.add(b3d);
        // Add the two buttons to the toolbar
        toolBar.add(b2d);
        toolBar.add(b3d);
        return toolBar;
    }
}
