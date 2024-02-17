package com.chuidiang.ejemplos.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Chuidiang
 * date 17/02/2024
 *
 * Ejemplos de Virtual Thread y Platform Thread
 */
public class VirtualThreadExample {
    public static void main(String[] args) throws InterruptedException {
        // Arranque de un Virtual Thread
        final Thread virtualThread = Thread.ofVirtual()
                .name("Virtual Thread")
                .start(() ->
                        System.out.printf("%s es virtual? %b\n",
                                Thread.currentThread().getName(),
                                Thread.currentThread().isVirtual())
                );
        virtualThread.join();

        // Una forma más rápida de arrancar un Virtual Thread, sin nombre
        final Thread thread = Thread.startVirtualThread(() ->
                System.out.printf("%s es virtual? %b\n",
                        Thread.currentThread().getName(),
                        Thread.currentThread().isVirtual())
        );
        thread.join();

        // Arranque de un Platform Thread.
        final Thread platformThread = Thread.ofPlatform()
                .name("Platform Thread")
                .start(() ->
                        System.out.printf("%s es virtual? %b\n",
                                Thread.currentThread().getName(),
                                Thread.currentThread().isVirtual()));
        platformThread.join();

        // Arranque de un Thread tradicional, tampoco es virtual
        Thread traditionalThread = new Thread(() ->
                System.out.printf("%s es virtual? %b\n",
                        Thread.currentThread().getName(), Thread.currentThread().isVirtual())
        );
        traditionalThread.setName("Traditional Thread");
        traditionalThread.start();
        traditionalThread.join();

        // Pool de Hilos virtuales
        try(ExecutorService executorService = Executors.newFixedThreadPool(10, Thread.ofVirtual().factory())) {
            executorService.execute(()->
                    System.out.printf("%s es virtual? %b\n",
                            Thread.currentThread().getName(), Thread.currentThread().isVirtual())
            );
        }
    }
}
