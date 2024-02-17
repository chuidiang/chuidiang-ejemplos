package com.chuidiang.ejemplos.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Chuidiang
 * date 17/02/2024
 *
 * Ejemplo de ThreadPoolExecutor, Executors y Virtual Threads
 */
public class ExecutorExample {
    /**
     * Lanza 100 Callable de distintas maneras usando las clases Java de hilos
     * @param args
     */
    public static void main(String[] args) {
        threadPoolExecutor("thread pool");
        executor("executor");
        virtualExecutor("virtual");
        threadPoolExecutorSubmit("thread pool submit");
    }

    /**
     * Lanza 100 Calables en un pool de 10 hilos que puede ampliarse hasta 100
     * @param name
     */
    private static void threadPoolExecutor(String name) {
        try(ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,100,0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>())){
            invokeCallables(threadPoolExecutor, name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ejemplo de lanzar Callable y Runnable con executorService.submit()
     *
     * @param name
     */
    private static void threadPoolExecutorSubmit(String name) {
        // Se instancia en try-with-resources para asegurar el cierre de todos los hilos al terminar.
        try(ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100,100,0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>())){

            // Se lanza un Runnable
            final Future<?> runnableResult = threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finalizado Submit de Runnable");
            });
            System.out.println(runnableResult.get());

            // Se lanza un Callable
            final Future<Double> callableResult = threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finalizado Submit de Callable");
                return Math.random();
            });
            // Se espera por el resultado y se saca por pantalla
            System.out.println(callableResult.get());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lanza 100 Callable en un pool de 10 hilos
     * @param name
     */
    private static void executor(String name) {
        try(ExecutorService executorService = Executors.newFixedThreadPool(10)){
            invokeCallables(executorService, name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lanza 100 Callable es virtual threads
     * @param name
     */
    private static void virtualExecutor(String name) {
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
            invokeCallables(executorService, name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Método útil para lanzar 100 Callable es un executorService
     *
     * @param executorService
     * @param name
     * @throws InterruptedException
     */
    private static void invokeCallables(ExecutorService executorService, String name) throws InterruptedException {
        List<Callable<Double>> callables = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            callables.add( () -> {
                Thread.sleep(1000);
                return Math.random();
            });
        }

        long t0 = System.currentTimeMillis();
        executorService.invokeAll(callables);
        System.out.println(name + " ha tardado "+ (System.currentTimeMillis()-t0)/1000.0);
    }
}
