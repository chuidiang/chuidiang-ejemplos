package com.chuidiang.examples.osgi_module1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JAVIER on 01/02/2017.
 */
public class Activator implements BundleActivator{
    private static final Logger log = LoggerFactory.getLogger(Activator.class);

    @Override
    public void start(BundleContext bundleContext) throws Exception {
       log.info("Module 1 Start");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
