package com.chuidiang.ejemplos.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Chuidiang
 * date 24/11/2023
 */
public class ClientSocketExample {
    public static void main(String[] args) {
        new ClientSocketExample();
    }

    public ClientSocketExample(){
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5557)){
            byte[] data = "Hello".getBytes();
            socket.getOutputStream().write(data);

            byte[] readBuffer = new byte[100];
            final int read = socket.getInputStream().read(readBuffer);
            if (read>0) {
                System.out.println(new String(readBuffer,0,read));
            } else {
                System.out.println("En el otro extremo han cerrado el socket");
                socket.close();
                return;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
