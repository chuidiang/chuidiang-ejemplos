package com.chuidiang.examples.luciad.lsp.interactive_label;

import com.chuidiang.examples.luciad.lsp.BasicLayerFactory;
import com.chuidiang.examples.luciad.lsp.BasicMap;
import com.chuidiang.examples.luciad.lsp.bussines_dada_2.MyLayerFactory;
import com.chuidiang.examples.luciad.lsp.bussines_dada_2.MyModel;
import com.luciad.view.lightspeed.controller.ILspController;
import com.luciad.view.lightspeed.controller.manipulation.TLspInteractiveLabelsController;
import com.luciad.view.lightspeed.layer.TLspCompositeLayerFactory;

import java.io.IOException;

/**
 * Etiqueta interactivas. Al pasar el ratón sobre una etiqueta, cambia por un
 * componente Swing que nosotros queramos (un JButton en este caso).
 * Es importante en InteractiveLabelProvider llamar a los fire.
 */
public class InteractiveLabelMain {
    public static void main(String[] args) throws IOException {
        BasicMap basicMap = new BasicMap(new TLspCompositeLayerFactory(
                new MyLayerFactory(),
                new BasicLayerFactory()));

        ILspController controller = basicMap.getView().getController();
        basicMap.getView().setController(null);
        controller.appendController(new TLspInteractiveLabelsController(new InteractiveLabelProvider()));
        basicMap.getView().setController(controller);
        basicMap.getView().addLayersFor(new MyModel());

        basicMap.createAndVisualize();
    }
}
