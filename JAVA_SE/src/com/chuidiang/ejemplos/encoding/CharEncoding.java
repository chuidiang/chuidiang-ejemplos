package com.chuidiang.ejemplos.encoding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.SortedMap;

/**
 * Ejemplos de codificacion de caracteres
 * @author Chuidiang
 * @date 04/03/2023
 */
public class CharEncoding {
    public static void main(String[] args) throws IOException {
        inMemorySamples();
        inFileSamples();
    }

    private static void inMemorySamples() {
        // Forma de obtener el CharSet por defecto.
        // El CharSet por defecto depende del sistema operativo, habitualmente suele ser UTF-8
        // Se puede cambiar, al arrancar la máquina virtual, con -Dfile.encoding=UTF16 o el que se deseé
        System.out.println("Default CharSet = " + Charset.defaultCharset().name() );

        // Forma de obtener todos los CharSet disponibles
        final SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        stringCharsetSortedMap.forEach((name,charset)->{
            System.out.println(name);
        });

        // La máquina virtual java, en mi caso, por defecto es UTF_8
        // Si el texto está pasado a bytes usando la tabla UTF_8
        byte[] bytes = "ñÑa".getBytes(StandardCharsets.UTF_8);
        // Se obtienen unos bytes [-61, -79, -61, -111, 97]
        System.out.println(Arrays.toString(bytes));
        // que la decodificación por defecto interpreta bien.
        System.out.println("UTF-8 con decodificación por defecto: " + new String(bytes));

        // pero si se pasan a bytes usando una codificación que  no es la de defecto
        bytes = "ñÑa".getBytes(StandardCharsets.UTF_16);
        // se obtienen unos bytes [-2, -1, 0, -15, 0, -47, 0, 97]
        System.out.println(Arrays.toString(bytes));
        // que la decodificación por defecto no entiende
        System.out.println("UTF-16 con decodificación por defecto: " + new String(bytes));
        // U otra decodifación distinta tampoco
        System.out.println("UTF-16 con decodificación ISO-8859-1: " + new String(bytes, StandardCharsets.ISO_8859_1));
        // y hay que decodificar correctamente
        System.out.println("UTF-16 con decodificación UTF-16: " + new String(bytes, StandardCharsets.UTF_16));
    }

    private static void inFileSamples() throws IOException {
        // Escribimos un fichero de texto, usando una codificación UTF-16
        final Path path = Paths.get("file.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_16)){
            writer.write("ñÑa");
        }

        // Leemos con la opción defecto de encoding de java, que en mi caso es UTF-8
        try(BufferedReader reader = Files.newBufferedReader(path)){
            System.out.print("Fichero UTF-16 leido con encondingo por defecto: ");
            try {
                System.out.println(reader.readLine());
            } catch (MalformedInputException exc) {
                System.out.println("Salta una excepción: "+exc.getMessage());
            }
        }

        // Leemos con la opción correcta, UTF-16
        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_16)){
            System.out.println("Fichero UTF-16 leido con enconding UTF-16: " + reader.readLine());
        }

    }
}
