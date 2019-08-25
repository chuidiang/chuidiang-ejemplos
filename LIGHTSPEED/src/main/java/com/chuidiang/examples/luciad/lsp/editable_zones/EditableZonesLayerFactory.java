package com.chuidiang.examples.luciad.lsp.editable_zones;

import com.chuidiang.examples.luciad.business_data.FlightPlanDataTypes;
import com.luciad.model.ILcdDataModelDescriptor;
import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelDescriptor;
import com.luciad.view.lightspeed.layer.ALspSingleLayerFactory;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspPaintState;
import com.luciad.view.lightspeed.layer.shape.TLspShapeLayerBuilder;
import com.luciad.view.lightspeed.painter.label.style.TLspDataObjectLabelTextProviderStyle;
import com.luciad.view.lightspeed.style.ILspWorldElevationStyle;
import com.luciad.view.lightspeed.style.TLspLineStyle;
import com.luciad.view.lightspeed.style.TLspTextStyle;

import java.awt.*;

public class EditableZonesLayerFactory extends ALspSingleLayerFactory {
    @Override
    public boolean canCreateLayers(ILcdModel aModel) {
        ILcdModelDescriptor md = aModel.getModelDescriptor();
        return md instanceof ILcdDataModelDescriptor &&
                ((ILcdDataModelDescriptor) md).getDataModel().equals(
                        EditableZonesDataTypes.getDataModel());
    }

    @Override
    public ILspLayer createLayer(ILcdModel aModel) {
        if (!canCreateLayers(aModel)) {
            return null;
        }

// Create a TLspLayer with the given model.
        TLspShapeLayerBuilder layerBuilder = TLspShapeLayerBuilder.newBuilder();
        layerBuilder.model(aModel);

        layerBuilder.bodyStyles(TLspPaintState.REGULAR,
// Use yellow lines, drape them onto the terrain
                TLspLineStyle.newBuilder()
                        .color(Color.YELLOW)
                        .width(2)
                        .elevationMode(ILspWorldElevationStyle.ElevationMode.ABOVE_ELLIPSOID)
                        .build()

        );


        layerBuilder.labelStyles(TLspPaintState.REGULAR,
// Use yellow text with a black outline
                TLspTextStyle.newBuilder()
                        .textColor(Color.YELLOW)
                        .haloColor(Color.BLACK)
                        .verticalSpacing(20)
                        .build(),
// Use the flight plan name as the label content
                TLspDataObjectLabelTextProviderStyle.newBuilder()
                        .expressions(
                                EditableZonesDataTypes.NAME)
                        .build()
        );


        layerBuilder.bodyEditable(true);
        return layerBuilder.build();

    }

}

