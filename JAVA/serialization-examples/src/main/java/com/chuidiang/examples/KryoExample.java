package com.chuidiang.examples;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayOutputStream;

/**
 * Created by chuidiang on 3/06/17.
 */
public class KryoExample {
    public static void main(String[] args) {
        Kryo serializer = new Kryo();

        // It's necessary for classes without default constructor
        serializer.setInstantiatorStrategy(
                new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        Output os = new Output(byteStream);
        SomeLittleClass instance = SomeLittleClass.newInstance(2);
        serializer.writeObject(os, instance);
        os.close();
        System.out.println(byteStream.toByteArray().length);

        Input input = new Input(byteStream.toByteArray());
        SomeLittleClass read = serializer.readObject(input, SomeLittleClass.class);
        System.out.println(read.toString());


        byteStream = new ByteArrayOutputStream();
        os =new Output(byteStream);
        SomeMediumClass mediumClass = new SomeMediumClass();
        serializer.writeObject(os,mediumClass);
        os.close();
        System.out.println(byteStream.toByteArray().length);

        input = new Input(byteStream.toByteArray());
        SomeMediumClass mediumClassRead = serializer.readObject(input, SomeMediumClass.class);
        System.out.println(mediumClassRead);
    }
}
