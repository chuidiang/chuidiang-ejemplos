package com.chuidiang.ejemplos.osgi_export.util;

import org.apache.log4j.Logger;

public class Saludo {
	private static final Logger log = Logger.getLogger(Saludo.class);
	private String saludo = "";

	public String getSaludo() {
		return saludo;
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}

	public void saluda() {
		log.info(saludo);
	}
}
