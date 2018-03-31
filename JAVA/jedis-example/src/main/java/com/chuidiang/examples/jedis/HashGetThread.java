package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Random;

public class HashGetThread extends Thread{

    public HashGetThread() {
        start();
    }

    public void run() {
        Random random = new Random();
        try (Jedis jedis = Pool.getResource()) {
            while (true) {
                List<String> lastUpdatedField = jedis.blpop(1, "lastUpdatedField");
                if (2 == lastUpdatedField.size()) {
                    System.out.println("hash[" + lastUpdatedField.get(1) + "]: " + jedis.hget("hash", lastUpdatedField.get(1)));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
