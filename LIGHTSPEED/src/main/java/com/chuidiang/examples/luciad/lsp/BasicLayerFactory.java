package com.chuidiang.examples.luciad.lsp;

import com.luciad.earth.model.TLcdEarthModelDescriptor;
import com.luciad.format.shp.TLcdSHPModelDescriptor;
import com.luciad.model.ILcdModel;
import com.luciad.view.lightspeed.layer.ALspSingleLayerFactory;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspPaintState;
import com.luciad.view.lightspeed.layer.raster.TLspRasterLayerBuilder;
import com.luciad.view.lightspeed.layer.shape.TLspShapeLayerBuilder;
import com.luciad.view.lightspeed.style.ILspWorldElevationStyle;
import com.luciad.view.lightspeed.style.TLspFillStyle;
import com.luciad.view.lightspeed.style.TLspLineStyle;

import java.awt.*;

public class BasicLayerFactory extends ALspSingleLayerFactory {

    @Override
    public boolean canCreateLayers(ILcdModel aModel) {
    // Check the model descriptor to see if this is a SHP model or an Earth
    // repository model
        return (aModel.getModelDescriptor() instanceof TLcdSHPModelDescriptor) ||
                (aModel.getModelDescriptor() instanceof TLcdEarthModelDescriptor);
    }

    @Override
    public ILspLayer createLayer(ILcdModel aModel) {
    // Create a layer depending on the type of model.
        if (aModel.getModelDescriptor() instanceof TLcdSHPModelDescriptor) {
    // Create a layer with the given model.
            TLspShapeLayerBuilder layerBuilder = TLspShapeLayerBuilder.newBuilder();
            layerBuilder.model(aModel);
//            layerBuilder.layerType(ILspLayer.LayerType.BACKGROUND);
            shpStyles(layerBuilder);

            return layerBuilder.build();
        } else if (aModel.getModelDescriptor() instanceof TLcdEarthModelDescriptor) {
    // Create a raster layer using its builder, using all default settings.
            return TLspRasterLayerBuilder.newBuilder().model(aModel).layerType(ILspLayer.LayerType.BACKGROUND).build();
        } else {
            return null;
        }
    }

    private void shpStyles (TLspShapeLayerBuilder layerBuilder){
        // Create the fill and line styles using builders. The elevation mode for both
// styles is set to ON_TERRAIN, so that the data will be draped over the 3D terrain
// when the view is set to 3D.
        TLspFillStyle fill = TLspFillStyle.newBuilder()
                .color(Color.GREEN)
                .elevationMode(ILspWorldElevationStyle.ElevationMode.
                        ON_TERRAIN)
                .stipplePattern(TLspFillStyle.StipplePattern.
                        HALF_TONE_2x2)
                .build();
        TLspLineStyle line = TLspLineStyle.newBuilder()
                .elevationMode(ILspWorldElevationStyle.ElevationMode.
                        ON_TERRAIN)
                .build();

        layerBuilder.bodyStyles(TLspPaintState.REGULAR, fill, line);
// Derive the selection styling from the regular stying, but change the color
        layerBuilder.selectable(false);
        layerBuilder.bodyStyles(TLspPaintState.SELECTED,
                fill.asBuilder().color(Color.RED).build(),
                line.asBuilder().color(Color.RED).width(3).build());
    }
}