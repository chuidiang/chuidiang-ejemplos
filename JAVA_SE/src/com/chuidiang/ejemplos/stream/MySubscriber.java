package com.chuidiang.ejemplos.stream;

import java.util.*;
import java.util.concurrent.Flow;

/**
 * @author Chuidiang
 * @date 15/04/2023
 */
public class MySubscriber implements Flow.Subscriber<Data>{
    private final int id;
    Flow.Subscription subscription;
    Set<String> statistics = new HashSet<>();
    Map<String, Integer> values = new HashMap<>();
    int valuesReceived = 0;

    public MySubscriber(int id){
        this.id=id;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Data item) {
        valuesReceived++;
        statistics.add(Thread.currentThread().getName());
        if (System.currentTimeMillis() - item.timeStamp > 1000 ){
            System.out.println("Tiro dato viejo");
            return;
        }

        Integer counter = values.get(Thread.currentThread().getName());
        if (null==counter){
            values.put(Thread.currentThread().getName(),1);
        } else {
            values.put(Thread.currentThread().getName(),counter+1);
        }
        try {
            if (6==id) {
                Thread.sleep(1500);
            } else {
                Thread.sleep(10);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("id = "+ id + " value = "+ item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        subscription.cancel();
        subscription=null;
//        printStatistics();
    }

    public void printStatistics() {
        System.out.println("subscriber "+id+" total=" +valuesReceived+" threads: "+ Arrays.toString(statistics.toArray()));
        statistics.forEach(threadName-> {
            System.out.print("id="+id + " name = " + threadName + " " + values.get(threadName)+ " - ");
        });
        System.out.println();
    }
}
