package com.chuidiang.examples.luciad.lsp.bussines_dada_2;

import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;

public class MyData extends TLcdLonLatHeightPoint {

    private String name;
    public MyData(double longitude, double latitude, String name){
        move2D(longitude,latitude);
        this.name=name;
    }

    public String toString(){
        return name;
    }
}
