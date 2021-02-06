package com.chuidiang.examples.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.Arrays;
import java.util.Date;

/**
 * @author fjabellan
 * @date 06/02/2021
 */
public class KryoMain {
    public static void main(String[] args) {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        Data data = new Data();
        data.setDate(new Date());

        BigData bigData = new BigData();
        bigData.setDoubleValue(1.3);
        bigData.setData(data);

        byte[] serialized = new byte[1000];
        Output output = new Output(serialized);

        kryo.writeClassAndObject(output,bigData);

        System.out.println("Se han escrito "+output.position());

        Input input = new Input(Arrays.copyOfRange(serialized,0, output.position()));
        Object o = kryo.readClassAndObject(new Input(serialized));

        if (o instanceof BigData){
            BigData read = (BigData)o;
            System.out.println(read.getDoubleValue());
            System.out.println(read.getData().getDate());
            System.out.println(read.getData().getValue());
        }
    }
}
