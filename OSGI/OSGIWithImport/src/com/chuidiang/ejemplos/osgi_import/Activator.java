package com.chuidiang.ejemplos.osgi_import;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.chuidiang.ejemplos.osgi_export.util.Saludo;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Saludo saludo = new Saludo();
		saludo.setSaludo("Hola desde plugin externo");
		saludo.saluda();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
