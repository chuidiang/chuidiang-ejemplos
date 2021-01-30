package com.chuidiang.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Rest {
    @Autowired
    Repository repository;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/accounts")
    List<Accounts> count(){
        Iterable<Accounts> dbAccounts = repository.findAll();
        List<Accounts> accounts = new ArrayList<>();
        dbAccounts.forEach(accounts::add);
        return accounts;
    }
}
