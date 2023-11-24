package com.chuidiang.ejemplos.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerSocket que está en espera de que se conecten clientes.
 * @author Chuidiang
 * date 24/11/2023
 */
public class ServerSocketExample {
    public static void main(String[] args) {
        new ServerSocketExample();
    }

    public ServerSocketExample(){
        // Nos ponemos a la escucha del puerto 5557 para aceptar clientse
        try(ServerSocket serverSocket = new ServerSocket(5557)){
            // Bucle indefinido para aceptar clientes de forma indefinida.
            while (true){
                // Nos quedamos bloqueados en espera de que un cliente se conecte.
                Socket client = serverSocket.accept();
                // Cuando un cliente se conecta, pasamos la instancia client a un hilo
                // separado para que atienda a este cliente que se acaba de conectar.
                new Thread(new ClientRunnable(client)).start();
            }
        } catch (IOException e){
            // Tratamiento de errores si hay problemas al ponernos a la escucha o aceptar
            // a un cliente.
            e.printStackTrace();
        }
    }
}
