package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Random;

public class HashGetThread extends Thread{
    private final Jedis jedis;

    public HashGetThread() {
        this.jedis = new Jedis("192.168.99.100",6379);
        start();
    }

    public void run() {
        Random random = new Random();
        while(true){
            List<String> lastFieldUpdated = jedis.blpop(1, "lastFieldUpdated");
            if (2==lastFieldUpdated.size()){
                System.out.println("hash["+lastFieldUpdated.get(1)+"]: "+jedis.hget("hash",lastFieldUpdated.get(1)));
            }
        }
    }
}
