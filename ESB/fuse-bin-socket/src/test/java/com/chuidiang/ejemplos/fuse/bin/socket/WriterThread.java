package com.chuidiang.ejemplos.fuse.bin.socket;

import java.io.OutputStream;

public class WriterThread extends Thread {

	private String label;
	private OutputStream output;
	private long delay;
	private byte[] data;

	public WriterThread(String label, OutputStream output, long delay, byte [] data) {
		this.label = label;
		this.output = output;
		this.delay = delay;
		this.data = data;
	}

	@Override
	public void run() {
		try {
			while (true) {
				output.write(data);
				System.out.println(label);
				Thread.sleep(delay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
