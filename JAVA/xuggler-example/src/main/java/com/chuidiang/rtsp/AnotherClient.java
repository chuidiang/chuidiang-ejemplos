package com.chuidiang.rtsp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AnotherClient extends Thread {
    private final Socket socket;

    public AnotherClient(Socket socket){
        this.socket = socket;
    }
    public void run(){
        try {

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                System.out.println(inputStream.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
