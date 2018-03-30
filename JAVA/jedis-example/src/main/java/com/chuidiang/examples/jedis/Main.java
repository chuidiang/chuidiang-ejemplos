package com.chuidiang.examples.jedis;

import redis.clients.jedis.Jedis;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        new KeyspaceEventsSubscriptorThread();


        new ListReaderThread();
        new SubscriberThread();
        new HashGetThread();

        new ListWriterThread();
        new PublisherThread();
        new HashSetThread();

        getInfo();
    }

    private static void getInfo() {
        try (Jedis jedis = Pool.getResource()) {
            System.out.println(jedis.info());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void setAndGet() {
        try (Jedis jedis = Pool.getResource()) {
            jedis.set("foo", "bar");
            String value = jedis.get("foo");
            System.out.println(value);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
