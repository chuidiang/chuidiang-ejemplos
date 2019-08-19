package com.chuidiang.examples.luciad.business_data.lsp;

import com.luciad.imaging.ALcdBasicImage;
import com.luciad.imaging.TLcdImageBuilder;
import com.luciad.imaging.TLcdImageModelDescriptor;
import com.luciad.model.ALcdModel;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.shape3D.TLcdLonLatHeightBounds;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class ImageModel extends ALcdModel {
    List<ALcdBasicImage> images = new ArrayList<>();
    public ImageModel(){
        RenderedImage image = new BufferedImage(100,100,BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = ((BufferedImage) image).getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0,0, image.getWidth(), image.getHeight());
        g.setColor(Color.CYAN);
        g.drawLine(0,0,100,100);
        TLcdImageBuilder imageBuilder = TLcdImageBuilder.newBuilder();
        imageBuilder.bounds(new TLcdLonLatHeightBounds(10,10,10,1,1,1));
        imageBuilder.imageReference(new TLcdGeodeticReference());
        imageBuilder.image(image);
        ALcdBasicImage basicImage = imageBuilder.buildBasicImage();
        images.add(basicImage);

        TLcdImageModelDescriptor descriptor = new TLcdImageModelDescriptor("imagen","imagen","imagen");
        setModelDescriptor(descriptor);
        setModelReference(new TLcdGeodeticReference());
    }
    @Override
    public Enumeration elements() {
        return Collections.enumeration(images);
    }
}
