package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class SubscriberThread extends Thread {
    private final Jedis jedis;

    public SubscriberThread() {
        this.jedis = new Jedis("192.168.99.100",6379);
        start();
    }

    public void run(){
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Subscriber : "+message);
            }
        },"channel");
    }
}
