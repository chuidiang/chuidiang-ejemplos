package com.chuidiang.examples.interfaces;

/**
 * Created by chuidiang on 30/11/17.
 */
public interface EventBusIfz {
    void publish(String topic, Object data);
    void consumer(String topic, ConsumerIfz consumer);
    long setPeriodic (long timer, PeriodicConsumerIfz consumer);
}
