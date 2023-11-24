package com.chuidiang.ejemplos.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Chuidiang
 * date 24/11/2023
 */
public class ClientThread implements Runnable {
    private static int counter=0;

    private final Socket client;

    public ClientThread(Socket client){
        this.client = client;
    }
    @Override
    public void run() {
        byte[] readBuffer = new byte[100];
        try {
            final int read = client.getInputStream().read(readBuffer);
            if (read>0) {
                System.out.println(new String(readBuffer,0,read));
            } else {
                System.out.println("En el otro extremo han cerrado el socket");
                client.close();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        counter++;
        if (counter%2 == 0){
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        byte[] data = "World".getBytes();
        try {
            client.getOutputStream().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
