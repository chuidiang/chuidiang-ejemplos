package com.chuidiang.examples.luciad.lsp.image;

import com.chuidiang.examples.luciad.lsp.BasicLayerFactory;
import com.chuidiang.examples.luciad.lsp.BasicMap;
import com.luciad.view.lightspeed.layer.TLspCompositeLayerFactory;

import java.io.IOException;

/**
 * Ejemplo de mapa básico al que se le añade una imagen hecha con código, en
 * ImageModel. Es un cuadrado azul oscuro con una diagonal cyan.
 */
public class ImageMain {
    public static void main(String[] args) throws IOException {
        BasicMap basicMap = new BasicMap(new TLspCompositeLayerFactory(
                new BasicLayerFactory(),
                new ImageLayerFactory()));

        basicMap.getView().addLayersFor(new ImageModel());

        basicMap.createAndVisualize();

    }
}
