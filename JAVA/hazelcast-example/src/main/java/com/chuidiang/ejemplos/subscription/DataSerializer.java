package com.chuidiang.ejemplos.subscription;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class DataSerializer implements StreamSerializer<Object>{

    @Override
    public void write(ObjectDataOutput objectDataOutput, Object data) throws IOException {
        Kryo kryo = new Kryo();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        kryo.writeClassAndObject(output, data);
        objectDataOutput.writeByteArray(output.getBuffer());
    }

    @Override
    public Object read(ObjectDataInput objectDataInput) throws IOException {
        Kryo kryo = new Kryo();
        byte [] buffer = objectDataInput.readByteArray();

        Input input = new Input(buffer);

        Object data = kryo.readClassAndObject(input);

        return data;
    }

    @Override
    public int getTypeId() {
        return 1;
    }

    @Override
    public void destroy() {

    }
}
