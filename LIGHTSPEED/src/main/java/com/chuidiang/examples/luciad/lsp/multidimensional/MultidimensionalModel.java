package com.chuidiang.examples.luciad.lsp.multidimensional;

import com.luciad.model.TLcd2DBoundsIndexedModel;
import com.luciad.multidimensional.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MultidimensionalModel extends TLcd2DBoundsIndexedModel implements ILcdMultiDimensionalModel {
    List<ILcdDimension<Date>> dimensions = new ArrayList<>();

    public MultidimensionalModel(){
        TLcdDimension.Builder<Date> builder = TLcdDimension.newBuilder();
        TLcdDimension<Date> dimension = builder.axis(TLcdDimensionAxis.TIME_AXIS).build();

        dimensions.add(dimension);
    }
    @Override
    public void applyDimensionFilter(TLcdDimensionFilter tLcdDimensionFilter, int i) {

    }

    @Override
    public TLcdDimensionFilter getDimensionFilter() {
        return null;
    }

    @Override
    public List<? extends ILcdDimension<?>> getDimensions() {
        return dimensions;
    }
}
