package com.chuidiang.examples;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author fjabellan 31/10/2023
 */
public class Bean {
    private String name;
    private Date birthday;
    private String email;

    public Bean(){
        // No hace Nada
    }

    public Bean(String name, Date birthday, String email) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
