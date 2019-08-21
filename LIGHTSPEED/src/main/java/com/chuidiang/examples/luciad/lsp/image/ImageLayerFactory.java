package com.chuidiang.examples.luciad.lsp.image;

import com.luciad.imaging.TLcdImageModelDescriptor;
import com.luciad.model.ILcdModel;
import com.luciad.view.lightspeed.layer.ALspSingleLayerFactory;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.raster.TLspRasterLayerBuilder;

public class ImageLayerFactory extends ALspSingleLayerFactory {
    @Override
    public ILspLayer createLayer(ILcdModel iLcdModel) {
        return TLspRasterLayerBuilder.newBuilder().model(iLcdModel).build();
    }

    @Override
    public boolean canCreateLayers(ILcdModel iLcdModel) {
        return iLcdModel.getModelDescriptor() instanceof TLcdImageModelDescriptor;
    }
}
