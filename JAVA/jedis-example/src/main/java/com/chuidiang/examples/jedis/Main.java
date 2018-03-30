package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

public class Main {

    private static Jedis jedis;

    public static void main(String[] args) throws InterruptedException {
        jedis = new Jedis("192.168.99.100",6379);

        new KeyspaceEventsSubscriptorThread();

        setAndGet();

        new ListReaderThread();
        new SubscriberThread();
        new HashGetThread();

        new ListWriterThread();
        new PublisherThread();
        new HashSetThread();

//        getInfo();
    }

    private static void getInfo() {
        System.out.println(jedis.info());
    }

    private static void setAndGet() {
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }
}
