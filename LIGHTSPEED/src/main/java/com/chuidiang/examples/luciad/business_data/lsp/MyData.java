package com.chuidiang.examples.luciad.business_data.lsp;

import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;

public class MyData extends TLcdLonLatHeightPoint {

    public MyData(double longitude, double latitude){
        move2D(longitude,latitude);
    }

    public String toString(){
        return "etiqueta";
    }
}
