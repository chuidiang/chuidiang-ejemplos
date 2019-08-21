package com.chuidiang.examples.luciad.lsp.bussines_dada_2;

import com.chuidiang.examples.luciad.lsp.BasicLayerFactory;
import com.chuidiang.examples.luciad.lsp.BasicMap;
import com.luciad.view.lightspeed.layer.TLspCompositeLayerFactory;

import java.io.IOException;

public class MyMain {
    public static void main(String[] args) throws IOException {
        BasicMap basicMap = new BasicMap(new TLspCompositeLayerFactory(
                new MyLayerFactory(),
                new BasicLayerFactory()));

        basicMap.getView().addLayersFor(new MyModel());

        basicMap.createAndVisualize();
    }
}
