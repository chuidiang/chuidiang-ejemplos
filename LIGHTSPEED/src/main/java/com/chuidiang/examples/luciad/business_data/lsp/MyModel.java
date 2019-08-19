package com.chuidiang.examples.luciad.business_data.lsp;

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
            data.add(new MyData(i,i));
        }
        setModelReference(new TLcdGeodeticReference());
        setModelDescriptor(new TLcdModelDescriptor("vassels","vessels","vessels"));
    }
    @Override
    public Enumeration elements() {
        return Collections.enumeration(data);
    }
}
