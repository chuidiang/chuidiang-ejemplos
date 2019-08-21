package com.chuidiang.examples.luciad.business_data;

import com.luciad.datamodel.ILcdDataObject;
import com.luciad.datamodel.TLcdDataObject;
import com.luciad.geodesy.TLcdEllipsoid;
import com.luciad.model.TLcd2DBoundsIndexedModel;
import com.luciad.shape.shape2D.TLcdLonLatCircle;
import com.luciad.shape.shape2D.TLcdLonLatPolygon;
import com.luciad.shape.shape2D.TLcdLonLatPolyline;
import com.luciad.shape.shape3D.ILcd3DEditablePoint;
import com.luciad.shape.shape3D.TLcd3DEditablePointList;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.util.ILcdFireEventMode;

import java.util.ArrayList;

public class Util {
    public static void addData(TLcd2DBoundsIndexedModel model){
        ArrayList<TLcdLonLatHeightPoint> points = new ArrayList<TLcdLonLatHeightPoint>();
        points.add(new TLcdLonLatHeightPoint(11,12,13));
        points.add(new TLcdLonLatHeightPoint(12,11,12));
        points.add(new TLcdLonLatHeightPoint(13,12,11));
        ILcd3DEditablePoint[] pointArray = new ILcd3DEditablePoint[points.size()];
        TLcd3DEditablePointList pointList = new TLcd3DEditablePointList(
                points.toArray(pointArray),false);


        ILcdDataObject plan = new TLcdDataObject(FlightPlanDataTypes.FLIGHT_PLAN_DATA_TYPE);
        plan.setValue(FlightPlanDataTypes.NAME, "planvuelo1");
        plan.setValue(FlightPlanDataTypes.THESHAPE, new TLcdLonLatPolyline(pointList));
        model.addElement(plan, ILcdFireEventMode.NO_EVENT);
    }

    public static void addCircles(TLcd2DBoundsIndexedModel model){


        TLcdEllipsoid ellipsoid = new TLcdEllipsoid();
        ILcdDataObject circle = new TLcdDataObject(FlightPlanDataTypes.FLIGHT_PLAN_DATA_TYPE);
        circle.setValue(FlightPlanDataTypes.NAME, "planvuelo2");
        circle.setValue(FlightPlanDataTypes.THESHAPE, new TLcdLonLatCircle(10,10,10000, ellipsoid));
        model.addElement(circle, ILcdFireEventMode.NO_EVENT);
    }

    public static void addZones(TLcd2DBoundsIndexedModel model) {
        ArrayList<TLcdLonLatHeightPoint> points = new ArrayList<TLcdLonLatHeightPoint>();
        points.add(new TLcdLonLatHeightPoint(10,13,1300));
        points.add(new TLcdLonLatHeightPoint(11,12,1200));
        points.add(new TLcdLonLatHeightPoint(12,13,1100));
        ILcd3DEditablePoint[] pointArray = new ILcd3DEditablePoint[points.size()];
        TLcd3DEditablePointList pointList = new TLcd3DEditablePointList(
                points.toArray(pointArray),false);

        ILcdDataObject zone = new TLcdDataObject(FlightPlanDataTypes.FLIGHT_PLAN_DATA_TYPE);
        zone.setValue(FlightPlanDataTypes.NAME,"the zone");
        zone.setValue(FlightPlanDataTypes.THESHAPE,new TLcdLonLatPolygon(pointList));
        model.addElement(zone,ILcdFireEventMode.NO_EVENT);
    }


}

