package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

public class ListWriterThread extends Thread {

    public ListWriterThread() {
        start();
    }

    public void run() {
        try (Jedis jedis = Pool.getResource()) {
            while (true) {
                double number = Math.random();
                jedis.rpush("list1", "ListWriter : " + Double.toString(number));
                Thread.sleep((long) (number * 1000));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
