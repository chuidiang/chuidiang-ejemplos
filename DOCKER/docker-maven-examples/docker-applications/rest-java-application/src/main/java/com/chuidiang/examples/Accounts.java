package com.chuidiang.examples;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name="accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="user_id")
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="email")
    private String email;
}
