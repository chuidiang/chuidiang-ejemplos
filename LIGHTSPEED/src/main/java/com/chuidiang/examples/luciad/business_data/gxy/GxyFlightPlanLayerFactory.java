package com.chuidiang.examples.luciad.business_data.gxy;

import com.chuidiang.examples.luciad.business_data.FlightPlanDataTypes;
import com.luciad.format.cgm.gxy.TLcdCGMFillGXYPainterStyle;
import com.luciad.model.ILcdDataModelDescriptor;
import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelDescriptor;
import com.luciad.view.gxy.*;
import com.luciad.view.lightspeed.editor.TLspShapeEditor;
import com.luciad.view.lightspeed.layer.ALspSingleLayerFactory;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspPaintState;
import com.luciad.view.lightspeed.layer.shape.TLspShapeLayerBuilder;
import com.luciad.view.lightspeed.painter.label.style.TLspDataObjectLabelTextProviderStyle;
import com.luciad.view.lightspeed.style.ILspWorldElevationStyle;
import com.luciad.view.lightspeed.style.TLspLineStyle;
import com.luciad.view.lightspeed.style.TLspTextStyle;

import java.awt.*;

public class GxyFlightPlanLayerFactory implements ILcdGXYLayerFactory {

    public boolean canCreateLayers(ILcdModel aModel) {
        ILcdModelDescriptor md = aModel.getModelDescriptor();
        return md instanceof ILcdDataModelDescriptor &&
                ((ILcdDataModelDescriptor) md).getDataModel().equals(
                        FlightPlanDataTypes.getDataModel());
    }

    @Override
    public ILcdGXYLayer createGXYLayer(ILcdModel aModel) {
        if (!canCreateLayers(aModel)) {
            return null;
        }
        TLcdGXYLayer layer = new TLcdGXYLayer(aModel);

        layer.setGXYPen(ALcdGXYPen.create(aModel.getModelReference()));


// Create a TLspLayer with the given model.

        TLcdGXYShapePainter painter = new TLcdGXYShapePainter();
        painter.setLineStyle(new TLcdGXYPainterColorStyle(Color.ORANGE,Color.YELLOW));
//        painter.setLineStyle(TLcdStrokeLineStyle.newBuilder().
//                color(Color.ORANGE).selectionColor(Color.YELLOW).build());
//        painter.setFillStyle(new TLcdCGMFillGXYPainterStyle(Color.RED));
        layer.setGXYPainterProvider(painter);

        TLcdGXYDataObjectLabelPainter labelPainter = new
                TLcdGXYDataObjectLabelPainter();
        labelPainter.setExpressions(FlightPlanDataTypes.NAME);
        labelPainter.setForeground(Color.WHITE);
        labelPainter.setHaloEnabled(true);
        labelPainter.setHaloColor(Color.BLACK);

        layer.setGXYLabelPainterProvider(labelPainter);
        layer.setLabeled(true);
        layer.setEditable(true);
        layer.setGXYEditorProvider(painter);
        layer.setGXYPen(ALcdGXYPen.create(aModel.getModelReference()));

        return layer;

    }

}

