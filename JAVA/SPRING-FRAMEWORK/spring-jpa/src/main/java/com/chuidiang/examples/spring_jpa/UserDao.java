package com.chuidiang.examples.spring_jpa;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Chuidiang
 * @date 15/07/2023
 */
@Transactional
public class UserDao {
    @PersistenceContext
    EntityManager entityManager;

    public void insert(){
        User pedro = new User();
        pedro.setName("Pedro");
        pedro.setEmail("pedro@email.com");
        entityManager.persist(pedro);
        User juan = new User();
        juan.setName("Juan");
        juan.setEmail("juan@email.com");
        entityManager.persist(juan);
    }

    public void query(){
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        List<User> resultList = query.getResultList();
        resultList.forEach(user -> {
            System.out.println(user.getId() + ", "+ user.getName() + ", "+ user.getEmail());
        });
    }
}
