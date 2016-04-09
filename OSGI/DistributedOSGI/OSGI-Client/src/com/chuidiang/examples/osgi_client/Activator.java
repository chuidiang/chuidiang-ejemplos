package com.chuidiang.examples.osgi_client;

import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.chuidiang.examples.osgi_interface.Adder;

public class Activator implements BundleActivator {

	private ServiceTracker tracker;
	private static Logger log = Logger.getLogger(Activator.class.getName());
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
	   // This ñapa is needed if you use ds equinox implementation !!!!!
	   tracker = new ServiceTracker(context, Adder.class.getName(), null);
      tracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	   log.info("Stopping client");
	}


}
