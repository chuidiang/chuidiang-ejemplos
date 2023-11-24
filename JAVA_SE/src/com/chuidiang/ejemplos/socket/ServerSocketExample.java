package com.chuidiang.ejemplos.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Chuidiang
 * date 24/11/2023
 */
public class ServerSocketExample {
    public static void main(String[] args) {
        new ServerSocketExample();
    }

    public ServerSocketExample(){
        try(ServerSocket serverSocket = new ServerSocket(5557)){
            while (true){
                Socket accept = serverSocket.accept();
                new Thread(new ClientThread(accept)).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
