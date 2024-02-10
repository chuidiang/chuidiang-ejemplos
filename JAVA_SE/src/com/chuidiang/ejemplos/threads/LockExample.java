package com.chuidiang.ejemplos.threads;

import java.text.MessageFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author Chuidiang
 * date 10/02/2024
 * Ejemplo con ReentrantLock.
 * Suponemos que CommoResource es un recurso compartido por varios hilos y que si
 * varios hilos llaman a la vez a su método doSomething() puede haber problemas.
 * Creamos 5 hilos que llamen a este método y para evitar el problema, bloqueamos el
 * recurso con ReentrantLock.
 */
public class LockExample {
    public static void main(String[] args) {
        // Para bloquear el recurso . El parámetro true hace que se libere al hilo
        // que más lleve esperando, es decir, más o menos en el orden de entrada.
        Lock lock = new ReentrantLock(true);

        // Recurso compartido
        CommonResource commonResource = new CommonResource();

        // Creación de los 5 hilos
        IntStream.range(0,5).forEach(i -> {
            new Thread(()-> {
                String text = MessageFormat.format("Soy {0}", i);
                try {
                    // una pequeña espera aleatoria para hacer que los hilos entren en
                    // un orden aleatorio
                    Thread.sleep((long)(Math.random()*1000));
                    System.out.println(text + " esperando");

                    // bloque del recurso o espera hasta que alguien lo libere.
                    lock.lock();
                    System.out.println(text + " dejo de esperar");

                    // usamos el recurso
                    commonResource.doSomething(text);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // liberamos el recurso, en el finally, para asegurarnos que se hace haya
                    // o no excepciones en el código anterior.
                    lock.unlock();
                }
            }).start();
        });

    }
}

/**
 * Un recurso "lento" al que se le dan datos y tarda algo en procesarlos.
 * Imaginamos que no admite que varios hilos le incordien a la vez....
 */
class CommonResource{
    public void doSomething(String text){
        // Una espera para simular que vamos lentos
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }
}
