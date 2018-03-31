package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class HashSetThread extends Thread{

    public HashSetThread() {
        start();
    }

    public void run() {
        Random random = new Random();
        try (Jedis jedis = Pool.getResource()) {
            while (true) {
                double value = Math.random();
                String field = "Field"+random.nextInt(10);
                jedis.hset("hash", field, Double.toString(value));
                jedis.lpush("lastUpdatedField", field);
                Thread.sleep((long) (1000 * value));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
