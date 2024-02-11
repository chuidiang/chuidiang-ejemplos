package com.chuidiang.ejemplos.flow_pub_sub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

/**
 * @author Chuidiang
 * @date 15/04/2023
 */
public class MyPublisher implements Flow.Publisher<Integer>{

    List<MySubscription> subscriptions = new ArrayList<>();

    public void submit (int value){
        subscriptions.forEach(subscription -> subscription.submit(value));
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        subscriptions.add(new MySubscription(subscriber));
    }

    class MySubscription implements Flow.Subscription{
        private final Flow.Subscriber<? super Integer> subscriber;
        private long requested = 0;
        private List<Integer> values = new ArrayList<>();

        public void submit(int value){
            values.add(value);
            send();
        }

        private void send() {
            while(requested>0 && !values.isEmpty()){
                subscriber.onNext(values.remove(0));
                requested--;
            }
        }

        public MySubscription(Flow.Subscriber<? super Integer> subscriber){
            this.subscriber = subscriber;
            subscriber.onSubscribe(this);
            while(requested>0 && !values.isEmpty()){
                subscriber.onNext(values.remove(0));
                requested--;
            }
        }
        @Override
        public void request(long n) {
            requested = requested + n;
        }

        @Override
        public void cancel() {
            System.out.println("Cancelado");
            subscriptions.remove(this);
        }
    }
}
