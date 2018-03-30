package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class ListReaderThread extends Thread {

    public ListReaderThread() {
        start();
    }

    public void run() {
        try (Jedis jedis = Pool.getResource()) {
            while (true){
                List<String> list = jedis.blpop(1,"list1");
                if (2==list.size()){
                    System.out.println("ListReader : "+list.get(1)); //list.get(0) gets the key
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
