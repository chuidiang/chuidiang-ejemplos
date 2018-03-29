package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

public class ListWriterThread extends Thread {
    private final Jedis jedis;

    public ListWriterThread() {
        this.jedis = new Jedis("192.168.99.100",6379);
        start();
    }

    public void run() {
        while (true){
            double number = Math.random();
                jedis.rpush("list1", "ListWriter : "+Double.toString(number));
            try {
                Thread.sleep((long)(number*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
