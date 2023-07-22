package com.chuidiang.examples.spring_jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Chuidiang
 * @date 15/07/2023
 */
public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByEmailOrName(String email, String name);
}
