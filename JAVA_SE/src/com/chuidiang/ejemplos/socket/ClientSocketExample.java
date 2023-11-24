package com.chuidiang.ejemplos.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * Ejemplo de socket cliente. Conecta con ServerSocketExample.
 * @author Chuidiang
 * date 24/11/2023
 */
public class ClientSocketExample {
    public static void main(String[] args) {
        new ClientSocketExample();
    }

    public ClientSocketExample(){
        // Establecemos la conexión con el servidor que suponemos esta arrancado en nuestro
        // mismo ordenador (IP localhost 127.0.0.1) y que escucha el puerto 5557
        try (Socket socket = new Socket("127.0.0.1", 5557)){
            // Array de bytes a enviar.
            byte[] data = "Hello".getBytes();
            // Envío del array de bytes.
            socket.getOutputStream().write(data);

            // Array de bytes para leer datos del socket
            byte[] readBuffer = new byte[100];
            // Lectura de datos del socket.
            final int read = socket.getInputStream().read(readBuffer);
            if (read>0) {
                // Si hemos leido algún byte, lo sacamos por pantalla.
                System.out.println(new String(readBuffer,0,read));
            } else {
                // Si no hemos leído bytes, es que han cerrado la conexión en el otro lado.
                // Sacamos el aviso por pantalla.
                System.out.println("En el otro extremo han cerrado el socket");
                return;
            }
        } catch (IOException e){
            // Tratamiento de errores en la lectura o escritura de datos.
            e.printStackTrace();
        }
    }
}
