package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class KeyspaceEventsSubscriptorThread extends Thread{
    private final Jedis jedis;

    public KeyspaceEventsSubscriptorThread() {
        this.jedis = new Jedis("192.168.99.100",6379);
        jedis.configSet("notify-keyspace-events","KEA");
        start();
    }

    public void run() {
        jedis.psubscribe(new JedisPubSub() {
            @Override
            public void onPMessage(String pattern, String channel, String message) {
                System.out.println("Keyspace Changes: "+pattern + " "+channel+" "+message);

            }

        },"__key*__:*");
    }
}
