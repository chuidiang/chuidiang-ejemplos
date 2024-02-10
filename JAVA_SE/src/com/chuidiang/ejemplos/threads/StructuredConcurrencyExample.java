package com.chuidiang.ejemplos.threads;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;
import java.util.stream.IntStream;

/**
 * @author Chuidiang
 * date 10/02/2024
 *
 * Ejemplo de StructuredTaskScope.
 * La lanzan varias "Task" que devuelven un número al azar tras un retardo aleatorio
 * StructuredTaskScope espera que terminen y suma los números de cada Task
 */
public class StructuredConcurrencyExample {
    public static void main(String[] args) {
        // Creacion del StructuredTaskScope que va a usar Taks que devuelven un Double
        try (StructuredTaskScope scope = new StructuredTaskScope<Double>()){

            // Creamos la lista de Tasks, la lanzamos con StructuredTaskScope.fork() y nos guardamos
            // en la lista el StructuredTaskScope.Subtask que nos devuelve.
            List<StructuredTaskScope.Subtask<Double>> tasks = new ArrayList<>();
            IntStream.range(0,5).forEach(i -> {
                final StructuredTaskScope.Subtask<Double> fork = scope.fork(new MyTask());
                tasks.add(fork);
            });

            // Esperamos que todas las task terminen
            scope.join();

            // Recogemos el resultado de cada StructuredTaskScope.Subtask y lo sumamos.
            double counter = 0.0;
            for (StructuredTaskScope.Subtask<Double> task : tasks) {
                counter = counter+task.get();
            }

            // Sacamos por pantalla el resultado.
            System.out.printf("Suma = %f\n",counter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Nuestro Task debe implementar Callable.
 * El método call() devuelve un Double aleatorio tras una espera de un tiempo aleatorio entre 0 y 1 segundo.
 * El retardo es para hacer esperar a StructuredTaskScope
 */
class MyTask implements Callable<Double>{
    @Getter
    private double value;
    @Override
    public Double call() throws Exception {
        Thread.sleep((long)(Math.random()*1000));
        return Math.random();
    }
}
