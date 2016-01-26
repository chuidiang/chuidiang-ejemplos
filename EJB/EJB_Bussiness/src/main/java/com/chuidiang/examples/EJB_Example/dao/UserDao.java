package com.chuidiang.examples.EJB_Example.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.chuidiang.examples.EJB_Example.model.User;

@Stateless
public class UserDao {
   private static Logger LOG = Logger.getLogger(UserDao.class.toString());

   @PersistenceContext(unitName = "bussiness")
   private EntityManager entityManager;

   public void addUser(User user) {
      entityManager.joinTransaction();
      entityManager.persist(user);
      LOG.info("Users inserted");
      List<User> users = entityManager.createQuery("select u from User u")
            .getResultList();
      LOG.info("Users="+users.size());
      
   }

   public List<User> getUsers() {
      entityManager.joinTransaction();
      List<User> users = entityManager.createQuery("select u from User u")
            .getResultList();
      return users;
   }
}
