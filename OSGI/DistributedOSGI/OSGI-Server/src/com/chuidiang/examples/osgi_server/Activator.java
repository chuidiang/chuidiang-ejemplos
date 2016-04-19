package com.chuidiang.examples.osgi_server;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class Activator implements BundleActivator {

	private Vertx vertx;
	private long periodic;

   /*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
	   vertx = Vertx.vertx();
	   periodic = vertx.setPeriodic(1000, e -> {
	      vertx.eventBus().publish("hola", "tu");
	   });
	   context.registerService(Vertx.class, vertx, null);
	   context.registerService(EventBus.class, vertx.eventBus(), null);
	   System.out.println("Registrado vertx");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	   vertx.cancelTimer(periodic);
	   vertx.close();
	}

}
