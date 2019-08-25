package com.chuidiang.examples.luciad.lsp.editable_zones;

import com.chuidiang.examples.luciad.lsp.BasicLayerFactory;
import com.chuidiang.examples.luciad.lsp.BasicMap;
import com.luciad.datamodel.ILcdDataObject;
import com.luciad.datamodel.TLcdDataObject;
import com.luciad.model.TLcd2DBoundsIndexedModel;
import com.luciad.model.TLcdDataModelDescriptor;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.shape2D.TLcdLonLatPolygon;
import com.luciad.shape.shape3D.ILcd3DEditablePoint;
import com.luciad.shape.shape3D.TLcd3DEditablePointList;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.util.ILcdFireEventMode;
import com.luciad.view.lightspeed.layer.TLspCompositeLayerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class EditableZonesMain {
    public static void main(String[] args) throws IOException {
        BasicMap map = new BasicMap(new TLspCompositeLayerFactory(
                new EditableZonesLayerFactory(), new BasicLayerFactory()));

        TLcd2DBoundsIndexedModel model = new TLcd2DBoundsIndexedModel();
        model.setModelReference(new TLcdGeodeticReference());

        TLcdDataModelDescriptor dataModelDescriptor=new TLcdDataModelDescriptor(
                "Zones",
                "Zones",
                "Zones",
                EditableZonesDataTypes.getDataModel(),
                Collections.singleton(EditableZonesDataTypes.EDITABLE_ZONES_DATA_TYPE),
                EditableZonesDataTypes.getDataModel().getTypes()
        );
        model.setModelDescriptor(dataModelDescriptor);

        addZones(model);

        map.getView().addLayersFor(model);

        map.createAndVisualize();
    }


    public static void addZones(TLcd2DBoundsIndexedModel model) {
        ArrayList<TLcdLonLatHeightPoint> points = new ArrayList<TLcdLonLatHeightPoint>();
        points.add(new TLcdLonLatHeightPoint(10,13,1300));
        points.add(new TLcdLonLatHeightPoint(11,12,1200));
        points.add(new TLcdLonLatHeightPoint(12,13,1100));
        ILcd3DEditablePoint[] pointArray = new ILcd3DEditablePoint[points.size()];
        TLcd3DEditablePointList pointList = new TLcd3DEditablePointList(
                points.toArray(pointArray),false);

        ILcdDataObject zone = new TLcdDataObject(EditableZonesDataTypes.EDITABLE_ZONES_DATA_TYPE);
        zone.setValue(EditableZonesDataTypes.NAME,"the zone");
        zone.setValue(EditableZonesDataTypes.THE_ZONE,new TLcdLonLatPolygon(pointList));
        model.addElement(zone, ILcdFireEventMode.NO_EVENT);
    }

}
