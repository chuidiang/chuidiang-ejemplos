package com.chuidiang.examples.xuggler;

import com.xuggle.xuggler.Configuration;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IContainerFormat;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IRational;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class XugglerRtmpReferenceImpl {

    private static String url = "rtmp://FJABELLAN1PW7/screen/";
    private static String fileName = "test/teststream";
    private static int framesToEncode = 60;
    private static int x = 0;
    private static int y = 0;
    private static int height = 480;
    private static int width = 640;

    public static void main(String[] args) {
        IContainer container = IContainer.make();
        IContainerFormat containerFormat_live = IContainerFormat.make();
        containerFormat_live.setOutputFormat("flv", url + fileName, null);
        container.setInputBufferLength(0);
        int retVal = container.open(url + fileName, IContainer.Type.WRITE, containerFormat_live);
        if (retVal < 0) {
            System.err.println("Could not open output container for live stream");
            System.exit(1);
        }
        IStream stream = container.addNewStream(0);
        IStreamCoder coder = stream.getStreamCoder();
        ICodec codec = ICodec.findEncodingCodec(ICodec.ID.CODEC_ID_H264);
        coder.setNumPicturesInGroupOfPictures(5);
        coder.setCodec(codec);
        coder.setBitRate(200000);
        coder.setPixelType(IPixelFormat.Type.YUV420P);
        coder.setHeight(height);
        coder.setWidth(width);
        System.out.println("[ENCODER] video size is " + width + "x" + height);
        coder.setFlag(IStreamCoder.Flags.FLAG_QSCALE, true);
        coder.setGlobalQuality(0);
        IRational frameRate = IRational.make(5, 1);
        coder.setFrameRate(frameRate);
        coder.setTimeBase(IRational.make(frameRate.getDenominator(), frameRate.getNumerator()));
        Properties props = new Properties();
        InputStream is = XugglerRtmpReferenceImpl.class.getResourceAsStream("/libx264-normal.ffpreset");
        try {
            props.load(is);
        } catch (IOException e) {
            System.err.println("You need the libx264-normal.ffpreset file from the Xuggle distribution in your classpath.");
            System.exit(1);
        }
        Configuration.configure(props, coder);
        coder.open();
        container.writeHeader();
        long firstTimeStamp = System.currentTimeMillis();
        long lastTimeStamp = -1;
        int i = 0;
        try {
            Robot robot = new Robot();
            while (i < framesToEncode) {
                //long iterationStartTime = System.currentTimeMillis();
                long now = System.currentTimeMillis();
                //grab the screenshot
                BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height));
                //convert it for Xuggler
                BufferedImage currentScreenshot = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
                currentScreenshot.getGraphics().drawImage(image, 0, 0, null);
                //start the encoding process
                IPacket packet = IPacket.make();
                IConverter converter = ConverterFactory.createConverter(currentScreenshot, IPixelFormat.Type.YUV420P);
                long timeStamp = (now - firstTimeStamp) * 1000;
                IVideoPicture outFrame = converter.toPicture(currentScreenshot, timeStamp);
                if (i == 0) {
                    //make first frame keyframe
                    outFrame.setKeyFrame(true);
                }
                outFrame.setQuality(0);
                coder.encodeVideo(packet, outFrame, 0);
                outFrame.delete();
                if (packet.isComplete()) {
                    container.writePacket(packet);
                    System.out.println("[ENCODER] writing packet of size " + packet.getSize() + " for elapsed time " + ((timeStamp - lastTimeStamp) / 1000));
                    lastTimeStamp = timeStamp;
                }
                System.out.println("[ENCODER] encoded image " + i + " in " + (System.currentTimeMillis() - now));
                i++;
                try {
                    Thread.sleep(Math.max((long) (1000 / frameRate.getDouble()) - (System.currentTimeMillis() - now), 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
        container.writeTrailer();
    }
}