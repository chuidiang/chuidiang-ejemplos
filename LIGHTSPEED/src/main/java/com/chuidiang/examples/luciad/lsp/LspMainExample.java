package com.chuidiang.examples.luciad.lsp;

import com.luciad.earth.model.TLcdEarthRepositoryModelDecoder;
import com.luciad.format.shp.TLcdSHPModelDecoder;
import com.luciad.geodesy.TLcdGeodeticDatum;
import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelDecoder;
import com.luciad.projection.TLcdEquidistantCylindrical;
import com.luciad.reference.TLcdGridReference;
import com.luciad.view.lightspeed.ILspAWTView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspViewBuilder;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.ILspLayerFactory;
import com.luciad.view.lightspeed.painter.grid.TLspLonLatGridLayerBuilder;
import com.luciad.view.lightspeed.util.TLspViewTransformationUtil;
import com.luciad.view.swing.TLcdLayerTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Collection;

public class LspMainExample {
    public static void main(String[] args) throws IOException {

        ILspAWTView aView = TLspViewBuilder.newBuilder().buildSwingView();
// Set layer factory of the view. When adding models to the view, this factory
// is used to create layers for those models.
        aView.setLayerFactory(createLayerFactory());

        JFrame fFrame = new JFrame("Luciad Lightspeed Fundamentals");
        fFrame.getContentPane().setLayout(new BorderLayout());
        fFrame.getContentPane().add(aView.getHostComponent(), BorderLayout.CENTER);
        fFrame.add(createToolBar(aView), BorderLayout.NORTH);
//        fFrame.add(new JScrollPane(createLayerControl(aView)), BorderLayout.EAST);
        fFrame.add(new TLcdLayerTree(aView),BorderLayout.WEST);

        fFrame.setSize(800, 600);
        fFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fFrame.setVisible(true);

        createShapefileModelAndAddLayers(aView);

        createTileModelAndAddLayers(aView);

        aView.addLayer(TLspLonLatGridLayerBuilder.newBuilder().build());


    }

    private static void createTileModelAndAddLayers(ILspAWTView aView) throws IOException {
        // Create a TLcdEarthRepositoryModelDecoder to decode Luciad Earth repositories
        ILcdModelDecoder earthDecoder = new TLcdEarthRepositoryModelDecoder();
        // Decode a sample data set (imagery data)
        ILcdModel earthModel = earthDecoder.decode("src/main/files/Data/Earth/SanFrancisco/tilerepository.cfg");
        // Calling addLayersFor() will cause the view to invoke its layer factory with
        // the given model and then add the resulting layers to itself
        aView.addLayersFor(earthModel);
    }

    private static void createShapefileModelAndAddLayers(ILspAWTView aView) throws IOException {
        // TLcdSHPModelDecoder can read ESRI SHP files
        ILcdModelDecoder decoder = new TLcdSHPModelDecoder();
        // Decode world.shp to create an ILcdModel
        ILcdModel shpModel = decoder.decode("src/main/files/Data/Shp/World/world.shp");
        // Calling addLayers() will cause the view to invoke its layer factory with
        // the given model and then add the resulting layers to itself
        Collection<ILspLayer> shpLayer = aView.addLayersFor(shpModel);
    }

    private static ILspLayerFactory createLayerFactory() {
        return new BasicLayerFactory();
    }

    private static JToolBar createToolBar(ILspView aView){
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
