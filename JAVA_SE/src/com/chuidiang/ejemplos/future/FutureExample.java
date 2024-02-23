package com.chuidiang.ejemplos.future;

import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * @author fjabellan 19/02/2024
 * Ejemplos con Future y CompletableFuture
 */
public class FutureExample {
    /** Hace llamadas a cada uno de los ejemplos */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        futureTaskRunnable();
//        futureTaskCallable();
//        futureTaskCallableCancel();
//        futureTaskCallableTimeout();
//        completableFuture();
        completableFutureJoin();
//        completableFutureJoinOfAll();


        Thread.sleep(2000);
    }

    /**
     * Crea un FutureTask pasándole un Callable. Los Callable devuelven un valor.
     * Lanza el FutureTask en un hilo y espera el resultado.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureTaskCallable() throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(() ->{
            Thread.sleep(1000);
            return "Done!";
        });

//        final ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.submit(future);
        new Thread(future).start();

        while(!future.isDone()){
            Thread.sleep(100);
        }
        System.out.println("Future Callable" + future.get());
    }

    /**
     * Crea un FutureTask pasándole un Runnable y el valor que debe devolver cuanto termine
     * el Runnable.
     * Lanza el FutureTask en un hilo y espera el resultado.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureTaskRunnable() throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Done!");

        new Thread(future).start();

        while(!future.isDone()){
            Thread.sleep(100);
        }
        System.out.println("Ruture Runnable "+future.get());
    }

    /**
     * Lanza un FutureTask y antes de que termine lo concela.
     * Vemos que salta una CancellationException si intentamos obtener el valor.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureTaskCallableCancel() throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                System.out.println("FutureTaskCallable Interrumpido");
                return null;
            }
            return "Done!";
        });

        new Thread(future).start();

        Thread.sleep(100);
        future.cancel(true);

        try {
            System.out.println("Future Callable" + future.get());
        } catch (CancellationException e) {
            System.out.println("Cancelado "+e.getMessage());
        }
    }

    /**
     * Una CompletableFuture nos permite no quedarnos es espera del resultado.
     * Con el método thenAccept(), recibiremos el resultado cuando esté disponible.
     */
    private static void completableFuture() {

        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("CompletableFuture Interrumpido");
                return null;
            }
            return "Done!";
        });
        completableFuture.thenAccept(value -> System.out.println("Terminado " + value));

        System.out.println("No estoy esperando");
    }

    /**
     * Lanza un FutureTask y espera por el resultado un tiempo máximo. Si pasa el tiempo y
     * no hemos obtenido el resultado, se lanza una TimeoutException
     * @throws ExecutionException
     */
    private static void futureTaskCallableTimeout() throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                System.out.println("FutureTaskCallable Interrumpido");
                return null;
            }
            return "Done!";
        });

        new Thread(future).start();

        try {
            System.out.println("Future Callable" + future.get(500, TimeUnit.MILLISECONDS));
        } catch (TimeoutException e) {
            System.out.println("Timeout "+e);
        }
    }

    private static void completableFutureJoin() {

        final CompletableFuture<String> completableReturn = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("CompletableFuture Interrumpido");
                throw new RuntimeException("Interrumpido");
            }
            return "Done!";
        });
        final CompletableFuture<String> completableException = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("Fallo");
        });
//        System.out.println(completableReturn.join());
//        System.out.println(completableException.join());
        Stream.of(completableReturn, completableException)
                .map(future-> future.join())
                .forEach(value -> System.out.println(value));
    }

    private static void completableFutureJoinOfAll() {

        final CompletableFuture<String> completableReturn = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("CompletableFuture Interrumpido");
                throw new RuntimeException("Interrumpido");
            }
            return "Done!";
        });
        final CompletableFuture<String> completableException = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("Fallo");
        });
        try {
            CompletableFuture.allOf(completableException, completableReturn).join();
        } catch (Exception e){
            System.out.println("allOf falla");
            return;
        }
        System.out.println(completableReturn.join());
        System.out.println(completableException.join());
    }

}
