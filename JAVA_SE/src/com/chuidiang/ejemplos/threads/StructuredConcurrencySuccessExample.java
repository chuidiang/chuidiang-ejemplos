package com.chuidiang.ejemplos.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.stream.IntStream;

/**
 * @author Chuidiang
 * date 10/02/2024
 *
 * Ejemplo de StructuredTaskScope.ShutdownOnFailure.
 * Similar al ejemplo de StructuredConcurrencyExample.java, pero haciendo que el proceso termine
 * en cuanto termina la primera subtarea
 */
public class StructuredConcurrencySuccessExample {
    public static void main(String[] args) {
        // Creacion del StructuredTaskScope que va a usar Taks que devuelven un Double
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<Double>()){

            // Creamos la lista de Tasks, la lanzamos con StructuredTaskScope.fork() y nos guardamos
            // en la lista el StructuredTaskScope.Subtask que nos devuelve.
            var tasks = IntStream.range(0,5)
                    .mapToObj(i ->  scope.fork(new MySuccessTask()))
                    .toList();

            // Esperamos que todas las task terminen
            scope.join();

            // Sacamos por pantalla el resultado.
            System.out.printf("Primero en terminar = %f\n",scope.result());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Nuestro Task debe implementar Callable.
 * El método call() devuelve un Double aleatorio tras una espera de un tiempo aleatorio entre 0 y 1 segundo.
 * El retardo es para hacer esperar a StructuredTaskScope
 * Cuando termine la primera tarea, veremos que se saca por pantalla la InterruptedException de las demas
 */
class MySuccessTask implements Callable<Double>{
    @Override
    public Double call() throws Exception {
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e){
            e.printStackTrace();
            throw e;
        }
        final double random = Math.random();
        System.out.println(random);
        return random;
    }
}
