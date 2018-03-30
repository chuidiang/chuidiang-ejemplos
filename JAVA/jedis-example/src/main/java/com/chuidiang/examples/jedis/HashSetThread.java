package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class HashSetThread extends Thread{
    private final Jedis jedis;

    public HashSetThread() {
        this.jedis = new Jedis("192.168.99.100",6379);
        start();
    }

    public void run() {
        Random random = new Random();
        while(true){
            double value = Math.random();
            int key = random.nextInt()%10;
            jedis.hset("hash", Integer.toString(key), Double.toString(value));
            jedis.lpush("lastFieldUpdated",Integer.toString(key));
            try {
                Thread.sleep((long)(1000*value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
