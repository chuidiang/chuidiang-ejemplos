package com.chuidiang.examples.luciad.business_data.lsp;

import java.util.ArrayList;
import java.util.List;

public class VesselEngine {
    private final VesselModel model;
    private List<Vessel> vessels = new ArrayList<>();

    public VesselEngine(VesselModel model){
        for (int i=1;i<15;i++) {
            Vessel vessel = new Vessel();
            vessel.setId(i);
            vessel.setName("name "+i);
            vessel.setHeading(i*3);
            vessel.setLongitude(i);
            vessel.setLatitude(i);
            vessels.add(vessel);
            model.addorUpdateVessel(vessel);
        }
        this.model=model;
    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                        vessels.forEach( vessel ->{
                            double heading=Math.toRadians(vessel.getHeading());
                            vessel.setLatitude(vessel.getLatitude()+0.001*Math.cos(heading));
                            vessel.setLongitude(vessel.getLongitude()+0.001*Math.sin(heading));
                            model.addorUpdateVessel(vessel);
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
