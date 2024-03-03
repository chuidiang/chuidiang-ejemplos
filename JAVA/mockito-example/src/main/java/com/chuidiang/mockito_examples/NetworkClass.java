package com.chuidiang.mockito_examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Clase que lee un string de un socket.
 * @author chuidiang
 * @date 15/11/2020
 */
public class NetworkClass {
    public String getStringFromRemoteServer() throws IOException {
        try (Socket socket = new Socket("123.23.45.67",33)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final String line = bufferedReader.readLine();
            return line;
        }
    }
}
