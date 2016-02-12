package com.chuidiang.examples.EJB_Example;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.chuidiang.examples.ejb.StatelesBean;

@RunWith(Arquillian.class)
@Ignore
public class TestNewSessionBean {
   
   @Deployment(name="dep2", order=2)
   public static JavaArchive createDeployment2() {
       return ShrinkWrap.create(JavaArchive.class,"test2.jar")
           .addClasses(StatelessBeanImpl.class,StatefullBean.class,StatelesBean.class)
//           .setManifest(new StringAsset("Dependencies:com.chuidiang.examples.ejb_interfaces"))
           .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
       
   }

   @EJB
   private StatelesBean statelessBeanImpl;

   @EJB
   private StatefullBean statefulBean;
   
   @Test 
   public void testSayHello() {
      Assert.assertNotNull(statelessBeanImpl);
      Assert.assertEquals(" Hello from an EJB !! ", statelessBeanImpl.sayHello());
      Assert.assertNotNull(statefulBean);
      statefulBean.setValue(11);
      Assert.assertEquals(11, statefulBean.getValue());
   }

}
