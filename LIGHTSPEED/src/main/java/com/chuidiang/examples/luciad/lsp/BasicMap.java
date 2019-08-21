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
import com.luciad.view.lightspeed.util.TLspViewTransformationUtil;
import com.luciad.view.swing.TLcdLayerTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Collection;

/**
 * Mapa basico con una imagen del mundo de fondo y las fronteras en
 * un shapefile.
 * Botonera para cambiar 2D/3D y panel con checks para visualizar/ocultar capas
 */
public class BasicMap {

    public ILspAWTView getView() {
        return view;
    }

    public JFrame getFrame() {
        return frame;
    }

    private final ILspAWTView view;
    private JFrame frame;

    public BasicMap(ILspLayerFactory layerFactory) throws IOException {
        view = TLspViewBuilder.newBuilder().buildAWTView();

        // Adds the background layer to the view and moves the grid layer to the top.
        view.setLayerFactory(layerFactory);

        createShapefileModelAndAddLayers(view);
        createTileModelAndAddLayers(view);
    }

    public void createAndVisualize(){
        frame = new JFrame("LuciadLightspeed AWT Business Data");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(view.getHostComponent(), BorderLayout.CENTER);
        frame.add(new JScrollPane(new TLcdLayerTree(view)), BorderLayout.EAST);
        frame.add(createToolBar(view), BorderLayout.NORTH);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
