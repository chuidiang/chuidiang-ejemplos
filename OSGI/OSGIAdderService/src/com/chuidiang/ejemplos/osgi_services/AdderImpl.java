package com.chuidiang.ejemplos.osgi_services;

import com.chuidiang.ejemplos.osgi_services.sumador.Adder;

/**
 * Implementacion de un servicio OSGI
 * 
 * @author Chuidiang
 *
 */
public class AdderImpl implements Adder {
	@Override
	public double add(double a,double b){
		return a+b;
	}
	
	public void startup(){
		System.out.println("Adder started");
		
	}
	public void shutdown() {
		System.out.println("Adder shutdown");
	}
}
