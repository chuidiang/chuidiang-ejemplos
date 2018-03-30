package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

public class PublisherThread extends Thread {

    public PublisherThread() {
        start();
    }

    public void run(){
        try (Jedis jedis = Pool.getResource()) {
            while (true) {
                double number = Math.random();
                jedis.publish("channel", "Publisher : " + Double.toString(number));
                try {
                    Thread.sleep((long) (number * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
