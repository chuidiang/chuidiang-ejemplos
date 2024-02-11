package com.chuidiang.ejemplos.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Chuidiang
 * date 11/02/2024
 * Ejemplos de las funciones habituales de Stream.
 */
public class StreamsExample {
    public static void main(String[] args) {
        Random random = new Random();

        // Obtener un stream de números aleatorios con Random.ints()
        IntStream ints = random.ints(20, 0, 10);

        // Se puede hacer un bucle de los elementos con foreach()
        ints.forEach(element -> System.out.print(element));
        System.out.println();

        // Una vez recorrido el stream, tenemos que crear uno nuevo, no podemos
        // volver a recorrerlo.
        ints = random.ints(20, 0, 10);

        // Se puede aplicar una funcion a cada elemento tambien con foreach()
        ints.forEach(System.out::print);
        System.out.println();

        ints = random.ints(20, 0, 10);
        // Podemos conseguir un stream filtrando, basta poner algo que devuelva true o false para
        // cada elemento
        ints.filter(element -> element>5).forEach(System.out::print);
        System.out.println();

        ints = random.ints(20, 0, 10);
        // Podemos obtener el primer elemento del stream con findFirst()
        OptionalInt first = ints.findFirst();
        if (first.isPresent()) {
            System.out.println(first.getAsInt());
        }

        // Con paralell() hacemos que las operaciones sobre el stream se hagan en paralelo
        ints = random.ints(20, 0, 10).parallel();
        // Podemos obtener cualquier elemento del stream con findAny()
        OptionalInt any = ints.findAny();
        if (any.isPresent()) {
            System.out.println(first.getAsInt());
        }

        ints = IntStream.range(0,10);
        // reduce() devuelve un unico valor tras recorer el stream.
        // En la primera llamada nos pasará el primer y segundo elemento y nosotros devolvemos un resultado
        // En las siguientes llamadas nos pasará el resultado que dimos en la pasada anterior y el siguiente elemento
        // El siguietne ejemplo suma todos los int
        OptionalInt reduce = ints.reduce((result, element) -> result + element);
        if (reduce.isPresent()){
            System.out.println("Reduce = " + reduce.getAsInt());
        }

        Stream<String> strings = Arrays.stream(new String[]{"a", "b", "c"});
        // Como el ejemplo anterior, concatenar cadenas
        Optional<String> reduce1 = strings.reduce((result, element) -> result + element);
        if (reduce1.isPresent()){
            System.out.println(reduce1.get());
        }

        strings = Arrays.stream(new String[]{"a", "b", "c"});
        // Otra forma es pasar una funcion que admita dos elemento.
        reduce1 = strings.reduce(String::concat);
        if (reduce1.isPresent()){
            System.out.println(reduce1.get());
        }

        final List<Data> data = List.of(new Data(1, "Pedro"), new Data(2, "Juan"), new Data(3, "Ana"));
        // collect() permite meter los elementos en un contenedor y nos devuelve el contenedor.
        // Por ejemplo, meter todos los elementos en una Collection, como un Map.
        // Se le pasan tres parametros:
        // - Algo que le permite crear el contenedor
        // - Algo que le permita meter elementos en el contenedor
        // - Algo que le permita añadir los elementos de un contenedor a otro. Esto solo tiene sentido si
        // se usan un steram paralelo, ya que cada hilo creará su propio contendor y luego necesitamemos
        // juntarlos todos.
        final Stream<Data> stream = data.stream();
        Map<Integer, Data> collect = data.parallelStream().collect(
                () -> new HashMap<>(),
                (c, e) -> c.put(e.id(), e),
                (c2, c) -> {
                    System.out.println("me llaman");
                    c2.putAll(c);
                }
        );
        collect.forEach((key,value)-> System.out.println(key + " - "+ value));

        // La Clase Collectors tiene métodos para los contendores habituales, como toMap() para un Map.
        // Se le pasan dos funciones
        // - Una que nos permita obtener la key del Map a partir de un elemento
        // - Una que nos permita obtener el value del Map a partir de un elemento.
        collect = data.stream().collect(Collectors.toMap(d->d.id(), d->d));
        collect.forEach((key,value)-> System.out.println(key + " - "+ value));

        // Las funciones habituales también se pueden pasar de esta forma.
        collect = data.stream().collect(Collectors.toMap(Data::id, Function.identity()));
        collect.forEach((key,value)-> System.out.println(key + " - "+ value));

        // anyMatch() devuelve true si cualquier elemento cumple la condición que le ponemos.
        // Hay allMatch() y noneMatch()
        ints = IntStream.range(0,10);
        System.out.println(ints.anyMatch(i -> i > 5));

        // La función map() nos permite transformar un elemento en otro cualquiera. Hay metodos
        // mas especificos como mapToDouble, etc. Obtenemos un Stream con los elementos transformados.
        ints = IntStream.range(0,10);
        IntStream doubleValues = ints.map(element -> element * 2);
        doubleValues.forEach(System.out::print);
        System.out.println();

        // Si los elementos son colecciones o tienen varios datos dentro, podemos obteenr con flatMap()
        // un stream en el que cada elemento se convierte en varios.
        final Stream<List<Integer>> listStream = List.of(List.of(1, 2, 3, 4), List.of(5, 6, 7, 8)).parallelStream();
        final Stream<Integer> integerStream = listStream.flatMap(e -> e.stream());
        integerStream.forEach(System.out::print);
        System.out.println();

        // En un stream paralelo trabajan varios hilos
        ints = random.ints(20, 0, 10).parallel();
        ints.parallel().forEach(e -> System.out.println(Thread.currentThread().getName() + " "+ e));


        final Stream<Integer> stream1 = List.of(1, 2, 3, 4, 5).stream();
        final Map<String, List<Integer>> collect1 = stream1.collect(Collectors.groupingBy(e -> e % 2==0?"par":"impar"));
        collect1.forEach((k,v)-> System.out.println(k + " "+ v));
    }
}
