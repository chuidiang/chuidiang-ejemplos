package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class SubscriberThread extends Thread {

    public SubscriberThread() {
        start();
    }

    public void run(){
        try (Jedis jedis = Pool.getResource()) {
            jedis.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    System.out.println("Subscriber : " + message);
                }
            }, "channel");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
