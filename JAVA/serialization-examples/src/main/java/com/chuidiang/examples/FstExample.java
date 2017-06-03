package com.chuidiang.examples;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import org.nustaq.serialization.util.FSTInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by chuidiang on 3/06/17.
 */
public class FstExample {
    public static void main(String[] args) throws Exception {
        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

        SomeMediumClass mediumClass = new SomeMediumClass();
        byte barray[] = conf.asByteArray(mediumClass);
        System.out.println(barray.length);
        SomeMediumClass object = (SomeMediumClass)conf.asObject(barray);
        System.out.println(object);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FSTObjectOutput output = new FSTObjectOutput(outputStream);
        output.writeObject(mediumClass);
        output.close();

        FSTObjectInput input = new FSTObjectInput(new ByteArrayInputStream(outputStream.toByteArray()));
        object = (SomeMediumClass)input.readObject(SomeMediumClass.class);
        System.out.println(object);
    }
}
