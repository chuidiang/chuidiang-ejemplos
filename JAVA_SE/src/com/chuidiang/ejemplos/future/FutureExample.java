package com.chuidiang.ejemplos.future;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author fjabellan 19/02/2024
 * Ejemplos con Future y CompletableFuture
 */
public class FutureExample {
    /** Hace llamadas a cada uno de los ejemplos */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException, IOException {
        futureTaskRunnable();
        futureTaskCallable();
        futureTaskCallableCancel();
        futureTaskCallableTimeout();
        completableFuture();
        completableFutureThenAcceptAsync();
        completableFutureChain();
        completableFutureJoin();
        completableFutureJoinException();
        completableFutureJoinOfAll();

        // Espera a que terminen todos los hilos antes de terminar el programa.
        ForkJoinPool.commonPool().awaitQuiescence(5, TimeUnit.SECONDS);
    }

    /**
     * Crea un FutureTask pasándole un Callable. Los Callable devuelven un valor.
     * Lanza el FutureTask en un hilo y espera el resultado.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureTaskCallable() throws ExecutionException, InterruptedException {
        System.out.println("Future Task Callable");
        FutureTask<String> future = new FutureTask<>(() ->{
            Thread.sleep(1000);
            return "Done!";
        });

        ForkJoinPool.commonPool().submit(future);

        while(!future.isDone()){
            Thread.sleep(100);
        }
        System.out.println("Future Task Callable devuelve " + future.get());
    }

    /**
     * Crea un FutureTask pasándole un Runnable y el valor que debe devolver cuanto termine
     * el Runnable.
     * Lanza el FutureTask en un hilo y espera el resultado.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureTaskRunnable() throws ExecutionException, InterruptedException {
        System.out.println("Future Task Runnable");
        FutureTask<String> future = new FutureTask<>(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Done!");

//        new Thread(future).start();
        ForkJoinPool.commonPool().submit(future);

        while(!future.isDone()){
            Thread.sleep(100);
        }
        System.out.println("Future Task Runnable devuelve "+future.get());
    }

    /**
     * Lanza un FutureTask y antes de que termine lo concela.
     * Vemos que salta una CancellationException si intentamos obtener el valor.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureTaskCallableCancel() throws ExecutionException, InterruptedException {
        System.out.println("Cancelando un Future Task Callable");
        FutureTask<String> future = new FutureTask<>(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                System.out.println("Future Task Callable interrumpido "+e);
                return null;
            }
            return "Done!";
        });

        ForkJoinPool.commonPool().submit(future);

        Thread.sleep(100);
        future.cancel(true);

        try {
            System.out.println("Future Task Callable devuelve" + future.get());
        } catch (CancellationException e) {
            System.out.println("Future Task Callable cancelado "+e);
        }
    }

    /**
     * Una CompletableFuture nos permite no quedarnos es espera del resultado.
     * Con el método thenAccept(), recibiremos el resultado cuando esté disponible.
     */
    private static void completableFuture() {
        System.out.println("Completable Future");
        CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Done!";
        }).thenAccept(value -> {
            sleep(1000);
            System.out.println("Completable Future devuelve " + value);
        });

        System.out.println("Completable Future. El hilo principal No espera resultado");
    }

    /**
     * Repetimos el ejemplo de completableFuture(), pero viendo la diferencia entre
     * thenAccept() y thenAcceptAsync().
     *
     * Si sypplyAsync() devuelve el resultado rapido (sin sleep), entonces thenAccept()
     * no lanza un nuevo hilo. El hilo principal se queda bloqueado hasta que thenAccept()
     * termine.
     *
     * Si usamos thenAcceptAsync(), se lanza un hilo nuevo indpendientemente que supplyAsync()
     * haya o no terminado. El hilo principal no espera.
     */
    private static void completableFutureThenAcceptAsync() {
        System.out.println("Completable Future Then Async 1");
        CompletableFuture.supplyAsync(() -> {
            return "Done!";
        }).thenAccept(value -> {
            sleep(1000);
            System.out.println("Completable Future Then Async 1 devuelve " + value);
        });
        System.out.println("Completable Future Then Async 1. El hilo principal se queda en espera de que se escriba el resultado");

        System.out.println("Completable Future Then Async 2");
        CompletableFuture.supplyAsync(() -> {
            return "Done!";
        }).thenAcceptAsync(value -> {
            sleep(1000);
            System.out.println("Completable Future Then Async 2 devuelve " + value);
        });
        System.out.println("Completable Future Then Async 2. El hilo principal no se queda en espera de que se escriba el resultado");
    }

    /**
     * Lanza varias CompletableFuture en la que cada una usa los resultados de la anterior.
     */
    private static void completableFutureChain(){
        System.out.println("CompletableFuture chain "+Thread.currentThread().getName());
        CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Done!";
        }).thenApplyAsync(value -> {
            sleep(1000);
            return value.toLowerCase();})
        .thenApplyAsync(value -> {
            System.out.println("CompletableFuture chain pasando a bytes "+Thread.currentThread().getName());
            sleep(1000);
            return value.getBytes();})
        .thenAcceptAsync(value -> {
            System.out.println("CompletableFuture chain preperando resultados para imprimir "+Thread.currentThread().getName());
            sleep(1000);
            System.out.println(Arrays.toString(value));
        });
        System.out.println("CompletableFuture chain el hilo principal no espera el resultado "+Thread.currentThread().getName());
    }

    /**
     * Lanza un FutureTask y espera por el resultado un tiempo máximo. Si pasa el tiempo y
     * no hemos obtenido el resultado, se lanza una TimeoutException
     * @throws ExecutionException
     */
    private static void futureTaskCallableTimeout() throws ExecutionException, InterruptedException {
        System.out.println("Future Task Callable con Timeout");
        FutureTask<String> future = new FutureTask<>(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                System.out.println("FutureTaskCallable Interrumpido "+e);
                return null;
            }
            return "Done!";
        });

        ForkJoinPool.commonPool().submit(future);

        try {
            String value = future.get(500, TimeUnit.MILLISECONDS);
            System.out.println("Future Task Callable devuelve " + value);
        } catch (TimeoutException e) {
            System.out.println("Future Task Callable Timeout "+e);
        }
    }

    /**
     * Se lanza un CompletableFuture y se espera por el resultado
     */
    private static void completableFutureJoin() {
        System.out.println("CompletableFuture join()");
        final CompletableFuture<String> completableReturn = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("CompletableFuture interrumpido "+e);
                throw new RuntimeException("Interrumpido");
            }
            return "Done!";
        });

        final String result = completableReturn.join();
        System.out.println("CompletableFuture devuelve " + result);
    }

    /**
     * Lanza un CompletableFuture que lanza una excepción pasado un tiempo, simulando que el proceso
     * no ha ido bien. Con exceptionally() nos recuperamos.
     */
    private static void completableFutureJoinException(){
        System.out.println("Completable Future con Exception");
        final CompletableFuture<String> completableException = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Integer.toString(1/0); // La división 1/0 lanza excepción
        }).exceptionally(e -> "Algo ha ido mal. La excepción  es "+e.getMessage());

        try {
            String value = completableException.join();
            System.out.println("Completable Future con Exception devuelve "+ value);
        } catch (CompletionException e){
            System.out.println("Completable Future con Exception da excepción "+e);
        }

    }

    /**
     * Lanza dos CompletableFuture. Una termina bien devolviendo un valor, la otra termina dando
     * una excepción.
     * allOf().join() da fallo global, puesto que ha fallado una de ellas.
     */
    private static void completableFutureJoinOfAll() {
        System.out.println("CompletableFuture join ofAll");
        final CompletableFuture<String> completableReturn = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("CompletableFuture join ofAll interrumpido "+e);
                throw new RuntimeException("Interrumpido");
            }
            return "Done!";
        });
        final CompletableFuture<String> completableException = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("Fallo");
        });

        try {
            CompletableFuture.allOf(completableException, completableReturn).join();
            System.out.println("CompletableFuture join ofAll, completableException devuelve "+completableException.join());
            System.out.println("CompletableFuture join ofAll, completableReturn devuelve "+completableReturn.join());
        } catch (Exception e){
            System.out.println("CompletableFuture join ofAll da fallo global "+ e);
        }
    }

    private static void sleep (long timeout){
        try {
            Thread.currentThread().sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
