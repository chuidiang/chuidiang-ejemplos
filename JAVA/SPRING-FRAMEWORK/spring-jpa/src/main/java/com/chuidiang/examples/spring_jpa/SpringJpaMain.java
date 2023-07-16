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

        UserDao userDao = factory.getBean(UserDao.class);
        userDao.insert();
        userDao.query();
    }
}
