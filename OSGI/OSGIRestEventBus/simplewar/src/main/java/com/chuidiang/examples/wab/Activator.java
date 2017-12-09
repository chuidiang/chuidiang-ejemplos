package com.chuidiang.examples.wab;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.logging.Logger;

/**
 * Created by chuidiang on 8/12/17.
 */
public class Activator implements BundleActivator {
    Logger logger = Logger.getLogger(getClass().getName());
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        logger.info("Activator started");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        logger.info("Activator stopped");
    }
}
