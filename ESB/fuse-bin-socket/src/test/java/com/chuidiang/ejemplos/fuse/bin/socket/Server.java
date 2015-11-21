package com.chuidiang.ejemplos.fuse.bin.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException {
		final ServerSocket ss = new ServerSocket(55560);
		new Thread() {
			public void run() {
				try {
					Socket client = ss.accept();
					new LectorThread("Server Lector", client.getInputStream(), client.getOutputStream()).start();
//					new WriterThread("Server Writer", client.getOutputStream(), 100, new byte[]{1,0,2,2,0}).start();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}.start();
	}
	

}
