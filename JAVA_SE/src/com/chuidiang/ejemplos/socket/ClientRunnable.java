package com.chuidiang.ejemplos.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

/**
 * Clase del servidor de socket para atender a clientes concretos.
 * @author Chuidiang
 * date 24/11/2023
 */
@Slf4j
public class ClientRunnable implements Runnable {
    // Instancia de socket del cliente concreto.
    private final Socket client;

    /** Constuctor, recibe la instancia Socket del cliente que tiene que tratar */
    public ClientRunnable(Socket client){
        this.client = client;
    }

    /** Método para envío y recepción de datos por el socket */
    @Override
    public void run() {
        // Un buffer para guardar los bytes que recibamos.
        byte[] readBuffer = new byte[100];
        try {
            String nombre = "Juan";
            if (log.isDebugEnabled()) {
                log.info("Hola, {} ", nombre);
            }
            // Lectura de datos. Se queda bloqueado hasta que haya datos disponibles o cierren
            // la conexión en el otro lado.
            final int read = client.getInputStream().read(readBuffer);
            if (read>0) {
                // Si hemos leido datos, los sacamos por pantalla.
                System.out.println(new String(readBuffer,0,read));
            } else {
                // Si no hemos leído datos, es que han cerrado la conexión, sacamos el aviso por pantalla
                // y cerramos nosotros también la conexión.
                System.out.println("En el otro extremo han cerrado el socket");
                client.close();
                return;
            }
        } catch (IOException e) {
            // Tratamiento de posibles errores al leer del socket.
            e.printStackTrace();
        }

        // Un buffer de datos para enviar
        byte[] data = "World".getBytes();
        try {
            // Envio del buffer de datos
            client.getOutputStream().write(data);
        } catch (IOException e) {
            // Tratamiendo de errores en el envío de datos.
            e.printStackTrace();
        }

        // Cerramos conexión con cliente, no vamos a tener más intercambio de datos con él
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
