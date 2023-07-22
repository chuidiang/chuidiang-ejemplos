package com.chuidiang.examples.spring_jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Chuidiang
 * @date 15/07/2023
 */
@Getter @Setter
@Entity
@Table(name="user_table")
@NamedQuery(name = "User.findByNameIgnoreCase", query = "SELECT u FROM User u WHERE LOWER(u.name) = LOWER(?1)")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;
    private String email;
}
