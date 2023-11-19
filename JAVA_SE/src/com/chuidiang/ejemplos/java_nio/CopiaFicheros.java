package com.chuidiang.ejemplos.java_nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Chuidiang
 * date 19/11/2023
 */
public class CopiaFicheros {
    public static void main(String[] args) {
        // Como fichero binario de ejemplo, usamos el mismo fichero .class compilado de esta clase.
        copia("target/classes/com/chuidiang/ejemplos/java_nio/CopiaFicheros.class",
                "target/classes/com/chuidiang/ejemplos/java_nio/CopiaFicheros.class.copy");
    }

    /** Copia de un fichero binario en otro */
    public static void copia(String ficheroOriginal, String ficheroCopia) {
        // Se abre el fichero original
        try (FileInputStream fileInput = new FileInputStream(ficheroOriginal)) {

            // Se abre el fichero donde se guardará la copia
            try (FileOutputStream fileOutput = new FileOutputStream(ficheroCopia)) {

                // Necesitamos un array de bytes para guardar lo leído
                byte[] array = new byte[1000];

                // Al leer, no tenemos garantía de leer todo el array, puede haber menos
                // bytes en el fichero. Así que guardamos el número de bytes leídos
                int leidos = fileInput.read(array);

                // Si se ha leído más de 0, se copian los leídos y se lee el siguiente bloque.
                while (leidos > 0) {
                    fileOutput.write(array, 0, leidos);
                    leidos = fileInput.read(array);
                }
                // Cuando se leen 0 bytes, es que el fichero se ha acabado.
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
