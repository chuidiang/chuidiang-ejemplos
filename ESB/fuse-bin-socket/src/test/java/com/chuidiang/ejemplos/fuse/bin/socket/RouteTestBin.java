package com.chuidiang.ejemplos.fuse.bin.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class RouteTestBin extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/blueprint-2.xml";
	}

	int received = 0;
	int responsed = 0;

	@Test
	public void testRoute() throws Exception {
		
		
		long now = System.currentTimeMillis();
		
		received = 0;
		responsed = 0;

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

		Socket s = new Socket("localhost", 55559);
		System.out.println("Connected");
		new LectorThread("Client Lector", s.getInputStream(),null).start();
		new WriterThread("Client Writer", s.getOutputStream(), 200, new byte[]{3,3,3,0}).start();

		System.in.read();
		System.out.println(System.currentTimeMillis()-now);
		

	}

}
