package com.chuidiang.examples.osgi_factory;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;

public class Activator implements BundleActivator {
   private static final Logger LOG = Logger
         .getLogger(Activator.class.getName());
   private BundleContext context;

   /*
    * (non-Javadoc)
    * 
    * @see
    * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
    */
   public void start(BundleContext theContext) throws Exception {
      this.context = theContext;
      
      new Thread() {
         public void run() {
            ComponentFactory factory = waitForFactory();
            createSeveralInstances(factory);
         }
      }.start();
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
    */
   public void stop(BundleContext context) throws Exception {
   }

   private ComponentFactory waitForFactory() {
      String filter = "(component.factory=com.chuidiang.examples.osgi_factory.adder_factory)";
      ServiceReference[] references=null;
      while (null == references) {
         try {
            Thread.sleep(1000);
            LOG.info("searching factory");
            references = context.getServiceReferences(
                  ComponentFactory.class.getName(), filter);

         } catch (Exception e) {
            LOG.severe("Error getting factory " + e.getMessage());
         }
      }
      return (ComponentFactory) context.getService(references[0]);
   }
   
   private void createSeveralInstances(ComponentFactory factory) {
      for (int i = 1; i < 5; i++) {
         try {
            Dictionary<String, String> configuration = new Hashtable<>();
            configuration.put("name", "Increment by " + i);
            configuration.put("increment", Integer.toString(i));
            factory.newInstance(configuration);
         } catch (Exception e) {
            LOG.severe("Error instantiating service " + e.getMessage());
         }
      }
   }
}
