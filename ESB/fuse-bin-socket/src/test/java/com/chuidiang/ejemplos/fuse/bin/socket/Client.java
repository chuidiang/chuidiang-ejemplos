package com.chuidiang.ejemplos.fuse.bin.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 55559);
		System.out.println("Connected");
		new LectorThread("Client Lector", s.getInputStream(),null).start();
		new WriterThread("Client Writer", s.getOutputStream(), 200, new byte[]{3,3,3,0}).start();

	}
}
