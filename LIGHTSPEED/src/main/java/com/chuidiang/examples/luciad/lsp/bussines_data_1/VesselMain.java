package com.chuidiang.examples.luciad.lsp.bussines_data_1;

import com.chuidiang.examples.luciad.lsp.BasicLayerFactory;
import com.chuidiang.examples.luciad.lsp.BasicMap;
import com.luciad.view.lightspeed.layer.TLspCompositeLayerFactory;

import java.io.IOException;

public class VesselMain {
    public static void main(String[] args) throws IOException {
        BasicMap basicMap = new BasicMap(new TLspCompositeLayerFactory(
                new VesselLayerFactory(),
                new BasicLayerFactory()));

        VesselModel model = new VesselModel();
        basicMap.getView().addLayersFor(model);

        basicMap.createAndVisualize();

        VesselEngine vesselEngine = new VesselEngine(model);
        vesselEngine.start();
    }
}
