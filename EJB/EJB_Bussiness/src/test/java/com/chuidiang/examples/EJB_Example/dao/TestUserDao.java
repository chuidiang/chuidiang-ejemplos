package com.chuidiang.examples.EJB_Example.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.validation.constraints.AssertTrue;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.chuidiang.examples.EJB_Example.model.User;

@RunWith(Arquillian.class)
public class TestUserDao {
   
   @Deployment(name="daoTest", order=2)
   public static JavaArchive createDeployment2() {
       return ShrinkWrap.create(JavaArchive.class,"daoTest.jar")
           .addClasses(UserDao.class,User.class)
           .addAsManifestResource("test-persistence.xml","persistence.xml")
           .addAsManifestResource("jbossas-ds.xml")
           .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
       
   }
   
   @EJB
   private UserDao userDao;
   
   @Test
   public void test() {
      User user = new User();
      user.setName("Pedro");
      user.setPassword("Secret");
      
      userDao.addUser(user);
      
      List<User> users = userDao.getUsers();
      
      Assert.assertNotNull(users);
      Assert.assertEquals("Users expected", 1, users.size());
   }

}
