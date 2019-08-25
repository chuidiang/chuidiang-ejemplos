package com.chuidiang.examples.luciad.lsp.editable_zones;

import com.luciad.datamodel.*;
import com.luciad.shape.ILcdPolygon;
import com.luciad.shape.TLcdShapeDataTypes;
import com.luciad.util.TLcdHasGeometryAnnotation;

/**
 * This class builds the structural description of the flight plan model, and provides
 * static access to it. The method getDataModel() provides the full flight plan data model.
 * The public constant FLIGHT_PLAN_DATA_TYPE refers to the only defined type of this model:
 * flight plans.
 */
public final class EditableZonesDataTypes {


    //The data model for the flight plans, fully describing the structure of the data.
    private static final TLcdDataModel EDITABLE_ZONES_DATA_MODEL;


    // The data model contains a single data type - the flight plan data type.
    public static final TLcdDataType EDITABLE_ZONES_DATA_TYPE;


    public static final String NAME = "zoneName"; //Starts with lower case, same as Java property
    public static final String THE_ZONE = "theZone";


    static final String EDITABLE_ZONES_TYPE = "EditableZonesType"; //Starts with capital, same as Java    class


    static {
// Assign the constants
        EDITABLE_ZONES_DATA_MODEL = createDataModel();
        EDITABLE_ZONES_DATA_TYPE = EDITABLE_ZONES_DATA_MODEL.getDeclaredType(EDITABLE_ZONES_TYPE);
    }


    private static TLcdDataModel createDataModel() {
// Create the builder for the data model.
// Use some unique name space, to prevent name clashes. This isn't really needed
// for the sample but might be useful when exposing it externally.
        TLcdDataModelBuilder builder = new TLcdDataModelBuilder(
                "http://www.mydomain.com/datamodel/EditableZonesModel");


        builder.typeBuilder("TheShape").superType(TLcdShapeDataTypes.SHAPE_TYPE).primitive(
                false).instanceClass(ILcdPolygon.class);


// Define the types and their properties (only one type and one property here)
        TLcdDataTypeBuilder editableZonesBuilder = builder.typeBuilder(EDITABLE_ZONES_TYPE);
        editableZonesBuilder.addProperty(NAME, TLcdCoreDataTypes.STRING_TYPE);
        editableZonesBuilder.addProperty(THE_ZONE, "TheShape");


// Finalize the creation
        TLcdDataModel dataModel = builder.createDataModel();


        TLcdDataType type = dataModel.getDeclaredType(EDITABLE_ZONES_TYPE);

// make sure LuciadLightspeed finds the geometry
        type.addAnnotation(new TLcdHasGeometryAnnotation(type.getProperty(THE_ZONE)));


        return dataModel;
    }


    public static TLcdDataModel getDataModel() {
        return EDITABLE_ZONES_DATA_MODEL;
    }


}