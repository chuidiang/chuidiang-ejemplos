package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class KeyspaceEventsSubscriptorThread extends Thread{

    public KeyspaceEventsSubscriptorThread() {
        start();
    }

    public void run() {
        try (Jedis jedis = Pool.getResource()) {

            jedis.configSet("notify-keyspace-events","KEA");

            jedis.psubscribe(new JedisPubSub() {
                @Override
                public void onPMessage(String pattern, String channel, String message) {
                    System.out.println("Keyspace Changes: " + pattern + " " + channel + " " + message);

                }

            }, "__key*__:*");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
