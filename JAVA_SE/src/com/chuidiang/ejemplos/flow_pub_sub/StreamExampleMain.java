package com.chuidiang.ejemplos.flow_pub_sub;

import java.util.stream.IntStream;

/**
 * @author Chuidiang
 * @date 15/04/2023
 */
public class StreamExampleMain {
    public static void main(String[] args) {
        IntStream.range(1,20).asLongStream().forEach(number-> System.out.println(number));
    }
}
