package com.chuidiang.rtsp;//com.chuidiang.rtsp.VideoStream

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class VideoStream {

    FileInputStream fis; //video file
    int frame_nb; //current frame nb

    //-----------------------------------
    //constructor
    //-----------------------------------
    public VideoStream(String filename) throws Exception{

        //init variables
//        fis = new FileInputStream(filename);
//        frame_nb = 0;
    }

    //-----------------------------------
    // getnextframe
    //returns the next frame as an array of byte and the size of the frame
    //-----------------------------------
    public int getnextframe(byte[] frame) throws Exception
    {

        BufferedImage image = getDesktopScreenshot();
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        ImageOutputStream outputStream = ImageIO.createImageOutputStream(bas);




// NOTE: The rest of the code is just a cleaned up version of your code

// Obtain writer for JPEG format
        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();

// Configure JPEG compression: 70% quality
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(0.7f);

// Set your in-memory stream as the output
        jpgWriter.setOutput(outputStream);

// Write image as JPEG w/configured settings to the in-memory stream
// (the IIOImage is just an aggregator object, allowing you to associate
// thumbnails and metadata to the image, it "does" nothing)
        jpgWriter.write(null, new IIOImage(image, null, null), jpgWriteParam);

// Dispose the writer to free resources
        jpgWriter.dispose();


        byte [] imageBytes = bas.toByteArray();
        System.out.println(imageBytes.length);
        for (int i=0;i<imageBytes.length;i++){
            frame[i]=imageBytes[i];
        }

        return imageBytes.length;

//        int length = 0;
//        String length_string;
//        byte[] frame_length = new byte[5];
//
//        //read current frame length
//        fis.read(frame_length,0,5);
//
//        //transform frame_length to integer
//        length_string = new String(frame_length);
//        length = Integer.parseInt(length_string);
//
//        return(fis.read(frame,0,length));
    }

    public static BufferedImage getDesktopScreenshot() {
        try {
            Robot robot = new Robot();
            Rectangle captureSize = new Rectangle(100,100);
            return robot.createScreenCapture(captureSize);
        }
        catch (AWTException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Dimension screenBounds = Toolkit.getDefaultToolkit().getScreenSize();

}