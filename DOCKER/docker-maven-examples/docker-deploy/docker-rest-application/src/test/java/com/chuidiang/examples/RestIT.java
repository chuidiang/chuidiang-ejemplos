package com.chuidiang.examples;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RestIT {
    static TestRestTemplate restTemplate;
    @BeforeClass
    public static void init(){
        restTemplate= new TestRestTemplate();
    }

    @Test
    public void whenSendingGet_thenMessageIsReturned() throws IOException {
        String url = "http://localhost:8080";
        URLConnection connection = new URL(url).openConnection();
        try (InputStream response = connection.getInputStream();
             Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.nextLine();
            Assert.assertEquals("Hello World!", responseBody);
        }
    }

    @Test
    public void whenGettingAccounts_thenAccountsAreReturned() throws IOException {
        String url = "http://localhost:8080/accounts";
        ResponseEntity<Map[]> response = restTemplate.getForEntity(url, Map[].class);
        Map[] accounts = response.getBody();
        Assert.assertNotNull(accounts);
        Assert.assertEquals(1,accounts.length);
        Map account = accounts[0];
        Assert.assertEquals("the username", account.get("username"));
        Assert.assertEquals("the password", account.get("password"));
        Assert.assertEquals("the email", account.get("email"));
    }

}