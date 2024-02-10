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
 * Similar al ejemplo de StructuredConcurrencyExample.java, pero haciendo que las subtareas lancen
 * una excepción esporáicamente para abortar el proceso completo.
 */
public class StructuredConcurrencyShutdownExample {
    public static void main(String[] args) {
        // Creacion del StructuredTaskScope que va a usar Taks que devuelven un Double
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()){

            // Creamos la lista de Tasks, la lanzamos con StructuredTaskScope.fork() y nos guardamos
            // en la lista el StructuredTaskScope.Subtask que nos devuelve.
            var tasks = IntStream.range(0,5)
                    .mapToObj(i ->  scope.fork(new MyShutdownTask()))
                    .toList();

            // Esperamos que todas las task terminen
            scope.join().throwIfFailed();

            // Recogemos el resultado de cada StructuredTaskScope.Subtask y lo sumamos.
            double counter = tasks.stream()
                    .mapToDouble(t -> t.get())
                    .sum();


            // Sacamos por pantalla el resultado.
            System.out.printf("Suma = %f\n",counter);
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
 */
class MyShutdownTask implements Callable<Double>{
    @Override
    public Double call() throws Exception {
        Thread.sleep((long)(Math.random()*1000));
        final double random = Math.random();
        if (random>0.8){
            throw new Exception("Falle");
        }
        System.out.println(random);
        return random;
    }
}
