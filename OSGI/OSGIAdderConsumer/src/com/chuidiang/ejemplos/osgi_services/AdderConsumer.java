package com.chuidiang.ejemplos.osgi_services;

import com.chuidiang.ejemplos.osgi_services.sumador.Adder;

/**
 * Ejemplo de clase que consume un servicio OSGI.
 * 
 * @author Chuidiang
 *
 */
public class AdderConsumer {

	private Adder sumador = null;

	public void setSumador(Adder sumador) {
		System.out.println("Adder received");
		this.sumador = sumador;
	}
	
	public void unsetSumador(Adder sumador){
		this.sumador=null;
	}

	public void start() {
		System.out.println("AdderConsumer started");
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (null != sumador) {
						System.out.println(sumador.add(Math.random(), Math.random()));
					} else {
						System.out.println("sumador es null");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop() throws Exception {
		System.out.println("Goodbye World!!");
	}

}
