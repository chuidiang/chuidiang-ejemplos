package com.chuidiang.ejemplos.flow_pub_sub;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author Chuidiang
 * @date 15/04/2023
 */
public class PubSubExampleMain {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Data> myPublisher = new SubmissionPublisher<>(new ForkJoinPool(),16);
        for (int i = 0; i<20; i++){
            myPublisher.subscribe(new MySubscriber(i));
        }

        for (int i=0;i<1000;i++){
            myPublisher.offer(new Data(System.currentTimeMillis(), i), (subscriber, value) -> {
                System.out.println("La has cagao" + value);
                return false;
            });
            Thread.sleep(100);
        }

        myPublisher.getSubscribers().forEach(subscriber -> ((MySubscriber)subscriber).printStatistics());
        myPublisher.close();
    }
}
