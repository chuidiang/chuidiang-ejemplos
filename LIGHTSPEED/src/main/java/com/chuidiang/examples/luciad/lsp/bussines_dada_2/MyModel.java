package com.chuidiang.examples.luciad.lsp.bussines_dada_2;

import com.luciad.model.ALcdModel;
import com.luciad.model.TLcdModelDescriptor;
import com.luciad.reference.TLcdGeodeticReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class MyModel extends ALcdModel {
    private List<MyData> data = new ArrayList<>();

    public MyModel(){
        for (int i=0;i<10;i++){
            data.add(new MyData(i,i,"name"+i));
        }
        setModelReference(new TLcdGeodeticReference());
        setModelDescriptor(new TLcdModelDescriptor("my model","my model","my model"));
    }
    @Override
    public Enumeration elements() {
        return Collections.enumeration(data);
    }
}
