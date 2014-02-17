package com.chuidiang.ejemplos.osgi_export;

import org.apache.log4j.BasicConfigurator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.chuidiang.ejemplos.osgi_export.util.Saludo;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		BasicConfigurator.configure();
		Saludo saludo = new Saludo();
		saludo.setSaludo("Hola");
		saludo.saluda();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
