package com.chuidiang.examples.luciad.business_data.lsp;

import com.luciad.model.TLcd2DBoundsIndexedModel;
import com.luciad.model.TLcdDataModelDescriptor;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.util.ILcdFireEventMode;
import com.luciad.util.concurrent.TLcdLockUtil;

import javax.swing.*;
import java.util.Collections;
import java.util.Enumeration;

public class VesselModel extends TLcd2DBoundsIndexedModel {
    public VesselModel(){
        TLcdDataModelDescriptor dataModelDescriptor=new TLcdDataModelDescriptor(
                "vessels",
                "vesselType",
                "Vessels",
                VesselDataTypes.getDataModel(),
                Collections.singleton(VesselDataTypes.VESSEL_DATA_TYPE),
                VesselDataTypes.getDataModel().getTypes()
        );
        setModelDescriptor(dataModelDescriptor);
        setModelReference(new TLcdGeodeticReference());
    }

    public void addorUpdateVessel(Vessel vessel){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VesselPoint vesselPoint=new VesselPoint(vessel);
                try (TLcdLockUtil.Lock lock = TLcdLockUtil.writeLock(this)) {
                    int idx = indexOf(vesselPoint);
                    if (-1!=idx) {
                        VesselPoint originalVesselPoint = (VesselPoint) elementAt(idx);
                        originalVesselPoint.setVessel(vessel);
                        elementChanged(originalVesselPoint, ILcdFireEventMode.FIRE_LATER);
                    } else {
                        addElement(vesselPoint, ILcdFireEventMode.FIRE_LATER);
                    }
                }
                fireCollectedModelChanges();
            }
        });

    }

    public void removeVessel(Vessel vessel){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VesselPoint vesselPoint=new VesselPoint(vessel);
                try (TLcdLockUtil.Lock lock = TLcdLockUtil.writeLock(this)) {
                    int idx = indexOf(vesselPoint);
                    if (-1!=idx){
                        removeElement(vesselPoint,ILcdFireEventMode.FIRE_LATER);
                    }
                }
                fireCollectedModelChanges();
            }
        });
    }

    public void removeVessel(long vesselId){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VesselPoint vesselToRemove = null;
                try (TLcdLockUtil.Lock lock = TLcdLockUtil.writeLock(this)) {
                    Enumeration elements = elements();
                    while (elements.hasMoreElements()){
                        VesselPoint point = (VesselPoint) elements.nextElement();
                        if (vesselId==point.getVessel().getId()){
                            break;
                        }
                    }
                    if (null!=vesselToRemove){
                        removeElement(vesselToRemove,ILcdFireEventMode.FIRE_LATER);
                    } else {
                        return;
                    }
                }
                fireCollectedModelChanges();
            }
        });
    }
}
