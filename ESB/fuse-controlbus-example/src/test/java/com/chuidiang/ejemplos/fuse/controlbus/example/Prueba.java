package com.chuidiang.ejemplos.fuse.controlbus.example;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Prueba {

	public static void main(String[] args) throws SocketException {
		Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
		while(networks.hasMoreElements()){
			NetworkInterface network = networks.nextElement();
			System.out.println(network.getName()+" "+network.toString());
			
		}

	}

}
