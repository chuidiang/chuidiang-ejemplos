package com.chuidiang.examples.luciad.business_data;

import com.luciad.datamodel.*;
import com.luciad.shape.ILcdShape;
import com.luciad.shape.TLcdShapeDataTypes;
import com.luciad.util.TLcdHasGeometryAnnotation;

/**
 * This class builds the structural description of the flight plan model, and provides
 * static access to it. The method getDataModel() provides the full flight plan data model.
 * The public constant FLIGHT_PLAN_DATA_TYPE refers to the only defined type of this model:
 * flight plans.
 */
public final class FlightPlanDataTypes {


    //The data model for the flight plans, fully describing the structure of the data.
    private static final TLcdDataModel FLIGHT_PLAN_DATA_MODEL;


    // The data model contains a single data type - the flight plan data type.
    public static final TLcdDataType FLIGHT_PLAN_DATA_TYPE;


    public static final String NAME = "name"; //Starts with lower case, same as Java property
    public static final String THESHAPE = "theshape";


    static final String FLIGHT_PLAN_TYPE = "FlightPlanType"; //Starts with capital, same as Java    class


    static {
// Assign the constants
        FLIGHT_PLAN_DATA_MODEL = createDataModel();
        FLIGHT_PLAN_DATA_TYPE = FLIGHT_PLAN_DATA_MODEL.getDeclaredType(FLIGHT_PLAN_TYPE);
    }


    private static TLcdDataModel createDataModel() {
// Create the builder for the data model.
// Use some unique name space, to prevent name clashes. This isn't really needed
// for the sample but might be useful when exposing it externally.
        TLcdDataModelBuilder builder = new TLcdDataModelBuilder(
                "http://www.mydomain.com/datamodel/FlightPlanModel");


        builder.typeBuilder("TheShapeType").superType(TLcdShapeDataTypes.SHAPE_TYPE).primitive(
                false).instanceClass(ILcdShape.class);


// Define the types and their properties (only one type and one property here)
        TLcdDataTypeBuilder flightPlanBuilder = builder.typeBuilder(FLIGHT_PLAN_TYPE);
        flightPlanBuilder.addProperty(NAME, TLcdCoreDataTypes.STRING_TYPE);
        flightPlanBuilder.addProperty(THESHAPE, "TheShapeType");


// Finalize the creation
        TLcdDataModel dataModel = builder.createDataModel();


        TLcdDataType type = dataModel.getDeclaredType(FLIGHT_PLAN_TYPE);

// make sure LuciadLightspeed finds the geometry
        type.addAnnotation(new TLcdHasGeometryAnnotation(type.getProperty(THESHAPE)));


        return dataModel;
    }


    public static TLcdDataModel getDataModel() {
        return FLIGHT_PLAN_DATA_MODEL;
    }


}