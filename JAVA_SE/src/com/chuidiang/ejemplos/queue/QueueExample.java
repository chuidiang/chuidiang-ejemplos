package com.chuidiang.ejemplos.queue;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Chuidiang
 * date 17/02/2024
 *
 * Ejemplo de Queue y BlockingQueue
 */
public class QueueExample {
    public static void main(String[] args) throws InterruptedException {
        // Creación de una Blocking Queue
        Queue<Integer> queue = new LinkedBlockingQueue<>(10);
        // Uso con métodos no bloqueantes
        offerAndPoll(queue);
        addAndRemove(queue);

        // Ahora la usamos como bloqueante
        BlockingQueue<Integer> blockingQueque = (BlockingQueue<Integer>)queue;

        // Métodos con bloqueo o timeout
        putAndTake(blockingQueque);
        offerAndPollWithTimeout(blockingQueque);
    }

    /**
     * Put intenta poner yn elemento en la cola y se bloquea si no tiene capacidad para mas elementos
     * Take intenta coger un elemento de la cola y se bloquea si esta vacía.
     *
     * @param blockingQueque
     * @throws InterruptedException
     */
    private static void putAndTake(BlockingQueue<Integer> blockingQueque) throws InterruptedException {
        System.out.println("---------- Put and Take -------------");
        final Thread producer = Thread.startVirtualThread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    blockingQueque.put(i);
                    System.out.println("Añadido " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread consumer = Thread.startVirtualThread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(100);
                    System.out.println("Leido " + blockingQueque.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.join();
        consumer.join();
    }

    /**
     * Add añade un elemento a la cola. Si la cola está llena, salta una excpeción.
     *
     * Remove borra un elemento de la cola. Si la cola está vacía, salta una excepción.
     *
     * Ninguno de los dos métodos se bloquea.
     *
     * @param queue
     */
    private static void addAndRemove(Queue<Integer> queue) {
        System.out.println("---------- Add and Remove -------------");
        for (int i = 0; i < 20; i++) {
            try {
                System.out.printf("add(%d) -> %s\n", i, queue.add(i));
            } catch (IllegalStateException e){
                System.out.printf("add(%d) -> %s\n", i, e.getMessage());
            }
        }

        for (int i = 0; i < 20; i++) {
            try {
                System.out.printf("remove(%d) -> %s\n", i, queue.remove());
            } catch (NoSuchElementException e){
                System.out.printf("remove(%d) -> %s\n", i, e.getMessage());
            }
        }
    }

    /**
     * Offer inenta añadir un elemento a la cola y devuelve true.
     * Si la cola esta llena, no añade el elemento y devuelve false.
     *
     * Poll recoge un elemento de la cola. Si la cola está vacia, devuelve null.
     *
     * Ninguno de los dos métodos se bloquea, simplemente devuelven null si la operación
     * no tiene éxito.
     *
     * @param queue
     */
    private static void offerAndPoll(Queue<Integer> queue) {
        System.out.println("---------- Offer and Poll -------------");
        for (int i = 0; i < 20; i++) {
            if (queue.offer(i)) {
                System.out.printf("offer(%d) -> %s\n", i, "Exito");
            } else {
                System.out.println("No se ha podido añadir "+i);
            }
        }

        for (int i = 0; i < 20; i++) {
            Integer value = queue.poll();
            if (null!=value) {
                System.out.printf("poll() -> %d\n", value);
            } else {
                System.out.println("No se ha podido recuperar "+i);
            }
        }
    }

    /**
     * Offer con timeout añade un elemento a la cola. Si la cola está llena, espera el tiempo indicado y
     * devuelve false si en ese tiempo sigue sin haber hueco en la cola. En caso de poder poner el
     * elemento en la cola, bien inmediatamente, bien antes de que pase el tiempo indicado, devuelve
     * true.
     *
     *
     * @param blockingQueque
     * @throws InterruptedException
     */
    private static void offerAndPollWithTimeout(BlockingQueue<Integer> blockingQueque) throws InterruptedException {
        System.out.println("---------- Offer and Poll con Timeout -------------");
        final Thread producer = Thread.startVirtualThread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    final boolean success = blockingQueque.offer(i, 10, TimeUnit.MILLISECONDS);
                    if(success) {
                        System.out.println("Puesto " + i);
                    } else {
                        System.out.println("Tiro "+ i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread consumer = Thread.startVirtualThread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    Thread.sleep(100);
                    Integer value =  blockingQueque.poll(100, TimeUnit.MILLISECONDS);
                    if (null!=value) {
                        System.out.println("Leido " + blockingQueque.poll(100, TimeUnit.MILLISECONDS));
                    } else {
                        System.out.println("No leo "+i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.join();
        consumer.join();
    }

}
