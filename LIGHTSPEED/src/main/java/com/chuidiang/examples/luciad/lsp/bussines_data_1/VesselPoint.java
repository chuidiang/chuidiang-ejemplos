package com.chuidiang.examples.luciad.lsp.bussines_data_1;

import com.luciad.datamodel.TLcdDataObject;
import com.luciad.internal.shape.TLinOrientedPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of={"id"})
@Data
public class VesselPoint extends TLcdDataObject {
    private Vessel vessel;
    private long id;

    public VesselPoint(Vessel vessel){
        super(VesselDataTypes.VESSEL_DATA_TYPE);
        TLinOrientedPoint orientedPoint = new TLinOrientedPoint(
                new TLcdLonLatHeightPoint(vessel.getLongitude(),
                        vessel.getLatitude(),0),vessel.getHeading()
        );
        setValue(VesselDataTypes.NAME,vessel.getName());
        setValue(VesselDataTypes.THESHAPE,orientedPoint);
        this.vessel=vessel;
        this.id=vessel.getId();
    }

    public String getName(){
        return vessel.getName();
    }

    public void setVessel(Vessel vessel){
        TLinOrientedPoint orientedPoint = (TLinOrientedPoint) getValue(VesselDataTypes.THESHAPE);
        TLcdLonLatHeightPoint point = (TLcdLonLatHeightPoint) orientedPoint.getFocusPoint();
        point.move3D(vessel.getLongitude(),vessel.getLatitude(),0);
    }
}
