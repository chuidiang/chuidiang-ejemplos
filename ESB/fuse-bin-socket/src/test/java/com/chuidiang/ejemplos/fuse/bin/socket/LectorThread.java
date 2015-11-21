package com.chuidiang.ejemplos.fuse.bin.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class LectorThread extends  Thread{

	private String label;
	private InputStream input;
	private OutputStream out;

	public  LectorThread(String label, InputStream input, OutputStream out) {
		this.label = label;
		this.input = input;
		this.out = out;
	}
	
	@Override
	public void run() {
		try {
		while (true){
			byte [] buffer = new byte[10];
			int read = input.read(buffer);
			if (read==-1){
				System.out.println(label+" read -1");
				return;
			}
			System.out.println(label + " received "+Arrays.toString(buffer));
			if (null!=out){
				for (int i=0;i<read;i++){
					if (buffer[i]==0){
						out.write(new byte[]{2,0});
					}
				}
			}
		}
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
