package com.chuidiang.examples.rest;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Created by chuidiang on 4/12/17.
 */
public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        bundleContext.registerService(RandomIfz.class, new RestRandom(), null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
