package com.chuidiang.examples.spring_jpa;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Chuidiang
 * @date 15/07/2023
 */
public class SpringJpaMain {
    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("spring-context.xml");
        final UserDao userDao = factory.getBean(UserDao.class);

        User newUser = new User();
        newUser.setName("Pedro");
        newUser.setEmail("pedro@email.com");
        userDao.save(newUser);
        newUser = new User();
        newUser.setName("Juan");
        newUser.setEmail("juan@email.com");
        userDao.save(newUser);

        System.out.println("Find All");
        Iterable<User> users = userDao.findAll();
        users.forEach(user -> {
            System.out.println(user.getId() + ", "+ user.getName() + ", "+ user.getEmail());
        });

        System.out.println("Find by Email:");
        User juan = userDao.findByEmail("juan@email.com");
        System.out.println(juan.getId() + ", "+ juan.getName() + ", "+ juan.getEmail());

        System.out.println("Find By Email or Name");
        users = userDao.findByEmailOrName("juan@email.com","Pedro");
        users.forEach(user -> {
            System.out.println(user.getId() + ", "+ user.getName() + ", "+ user.getEmail());
        });

        System.out.println("Find by Name Ignore Case:");
        User pedro = userDao.findByNameIgnoreCase("pEDRo");
        System.out.println(pedro.getId() + ", "+ pedro.getName() + ", "+ pedro.getEmail());
    }
}
