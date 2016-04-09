package com.chuidiang.examples.osgi_client;

import java.util.logging.Logger;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/* Not used. Only for test purposes */
public class CommonInterceptor extends AbstractPhaseInterceptor {

   private Logger LOGGER = Logger.getLogger(CommonInterceptor.class.getName());
   public CommonInterceptor() {
      super(Phase.MARSHAL);
   }
   
   @Override
   public void handleFault(Message message) {
      LOGGER.severe("Error !");
      
      message.clear();
   }

   @Override
   public void handleMessage(Message arg0) throws Fault {
      LOGGER.fine("Message received");
      
   }
}