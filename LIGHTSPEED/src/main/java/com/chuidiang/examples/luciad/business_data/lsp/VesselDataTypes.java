package com.chuidiang.examples.luciad.business_data.lsp;

import com.luciad.datamodel.*;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.TLcdShapeDataTypes;
import com.luciad.util.TLcdHasGeometryAnnotation;

public class VesselDataTypes {
    //The data model for the flight plans, fully describing the structure of the data.
    private static final TLcdDataModel VESSEL_DATA_MODEL;


    // The data model contains a single data type - the flight plan data type.
    public static final TLcdDataType VESSEL_DATA_TYPE;


    public static final String NAME = "vessel"; //Starts with lower case, same as Java property
    public static final String THESHAPE = "thevessel";


    static final String VESSEL_TYPE = "VesselType"; //Starts with capital, same as Java    class


    static {
// Assign the constants
        VESSEL_DATA_MODEL = createDataModel();
        VESSEL_DATA_TYPE = VESSEL_DATA_MODEL.getDeclaredType(VESSEL_TYPE);
    }


    private static TLcdDataModel createDataModel() {
// Create the builder for the data model.
// Use some unique name space, to prevent name clashes. This isn't really needed
// for the sample but might be useful when exposing it externally.
        TLcdDataModelBuilder builder = new TLcdDataModelBuilder(
                "http://www.mydomain.com/datamodel/VesselModel");

        builder.typeBuilder("TheShapeType").superType(TLcdShapeDataTypes.SHAPE_TYPE).primitive(
                false).instanceClass(ILcdPoint.class);

// Define the types and their properties (only one type and one property here)
        TLcdDataTypeBuilder vesselBuilder = builder.typeBuilder(VESSEL_TYPE);
        vesselBuilder.addProperty(NAME, TLcdCoreDataTypes.STRING_TYPE);
        vesselBuilder.addProperty(THESHAPE, "TheShapeType");


// Finalize the creation
        TLcdDataModel dataModel = builder.createDataModel();

        TLcdDataType type = dataModel.getDeclaredType(VESSEL_TYPE);

// make sure LuciadLightspeed finds the geometry
        type.addAnnotation(new TLcdHasGeometryAnnotation(type.getProperty(THESHAPE)));


        return dataModel;
    }


    public static TLcdDataModel getDataModel() {
        return VESSEL_DATA_MODEL;
    }

}
