package com.chuidiang.examples.luciad.lsp.bussines_dada_2;

import com.chuidiang.examples.luciad.business_data.VesselIcon;
import com.chuidiang.examples.luciad.lsp.bussines_data_1.VesselDataTypes;
import com.luciad.model.ILcdDataModelDescriptor;
import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelDescriptor;
import com.luciad.view.lightspeed.layer.ALspSingleLayerFactory;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspPaintState;
import com.luciad.view.lightspeed.layer.shape.TLspShapeLayerBuilder;
import com.luciad.view.lightspeed.painter.label.style.TLspDataObjectLabelTextProviderStyle;
import com.luciad.view.lightspeed.style.TLspIconStyle;
import com.luciad.view.lightspeed.style.TLspTextStyle;

import java.awt.*;

public class MyLayerFactory extends ALspSingleLayerFactory {
    @Override
    public ILspLayer createLayer(ILcdModel iLcdModel) {
        if (!canCreateLayers(iLcdModel)) {
            return null;
        }

// Create a TLspLayer with the given model.
        TLspShapeLayerBuilder layerBuilder = TLspShapeLayerBuilder.newBuilder();
        layerBuilder.model(iLcdModel);

        layerBuilder.bodyStyles(TLspPaintState.REGULAR,
                TLspIconStyle.newBuilder()
                        .icon(new VesselIcon())
                        .useOrientation(true).build()
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
                                VesselDataTypes.NAME)
                        .build()
        );

        layerBuilder.bodyEditable(true);

        // si se descomenta esta linea, las etiquetas se pueden arrastar con el ratón
        // y ponerlas donde queramos.
        // ayerBuilder.labelEditable(true);
        return layerBuilder.build();

    }

    @Override
    public boolean canCreateLayers(ILcdModel iLcdModel) {
        if (iLcdModel instanceof MyModel){
            return true;
        }
        return false;
    }
}
