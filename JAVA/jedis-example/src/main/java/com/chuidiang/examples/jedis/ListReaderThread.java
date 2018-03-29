package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class ListReaderThread extends Thread {
    private final Jedis jedis;

    public ListReaderThread() {
        this.jedis = new Jedis("192.168.99.100",6379);
        start();
    }

    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            List<String> list = jedis.blpop(1,"list1");
            if (2==list.size()){
                System.out.println("ListReader : "+list.get(1)); //list.get(0) gets the key
            }
        }
    }
}
