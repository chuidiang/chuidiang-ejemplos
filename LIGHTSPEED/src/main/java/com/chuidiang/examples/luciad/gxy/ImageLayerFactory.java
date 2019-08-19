package com.chuidiang.examples.luciad.gxy;

import com.luciad.imaging.ILcdImageModelDescriptor;
import com.luciad.model.ILcdModel;
import com.luciad.view.gxy.ALcdGXYPen;
import com.luciad.view.gxy.ILcdGXYLayer;
import com.luciad.view.gxy.ILcdGXYLayerFactory;
import com.luciad.view.gxy.TLcdGXYLayer;
import com.luciad.view.gxy.painter.TLcdGXYImagePainter;

public class ImageLayerFactory implements ILcdGXYLayerFactory {
    @Override
    public ILcdGXYLayer createGXYLayer(ILcdModel aModel) {
        if (!(aModel.getModelDescriptor() instanceof ILcdImageModelDescriptor)) {
            return null;
        }
        TLcdGXYLayer layer = new TLcdGXYLayer(aModel);
// Sets a pen on the layer.
        layer.setGXYPen(ALcdGXYPen.create(aModel.getModelReference()));

// Creates an ILcdGXYPainter to paint an ALcdBasicImage.
        TLcdGXYImagePainter painterProvider = new TLcdGXYImagePainter();
        painterProvider.setFillOutlineArea(true);
        layer.setGXYPainterProvider(painterProvider);
        layer.setSelectable(false);
        return layer;
    }
}
