package com.chuidiang.ejemplos.future;

import org.eclipse.jetty.util.Promise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author fjabellan 19/02/2024
 */
public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var future = futureTaskCallable();
        while(!future.isDone()){
            Thread.sleep(100);
        }
        System.out.println(future.get());

        future = futureTaskRunnable();
        while(!future.isDone()){
            Thread.sleep(100);
        }
        System.out.println(future.get());


    }

    private static Future<String> futureTaskCallable() throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(() ->{
            Thread.sleep(1000);
            return "Done!";
        });

        new Thread(future).start();

        return future;
    }

    private static Future<String> futureTaskRunnable() throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Done!");

        new Thread(future).start();

        return future;
    }

}
