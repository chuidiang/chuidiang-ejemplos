package com.chuidiang.examples.EJB_Example;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.chuidiang.examples.ejb.StatelesBean;

@RunWith(Arquillian.class)
public class TestNewSessionBean {

   @Deployment
   public static JavaArchive createDeployment() {
       return ShrinkWrap.create(JavaArchive.class,"test.jar")
           .addClasses(StatelessBeanImpl.class,StatefullBean.class)
           .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }
   
   @Inject
   private StatelesBean statelessBeanImpl;

   @EJB
   private StatefullBean statefulBean;
   
   @Test
   @InSequence(1)
   public void testSayHello() {
      Assert.assertNotNull(statelessBeanImpl);
      Assert.assertEquals("Hello", statelessBeanImpl.sayHello());
      Assert.assertNotNull(statefulBean);
      statefulBean.setValue(11);
      Assert.assertEquals(11, statefulBean.getValue());
   }

//   @Test
//   @InSequence(2)
//   public void testSayHello2() {
//      Assert.assertNotNull(statelessBeanImpl);
//      Assert.assertEquals("Hello", statelessBeanImpl.sayHello());
//      Assert.assertEquals(11, statefulBean.getValue());
//   }
}
