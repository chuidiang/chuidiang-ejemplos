package com.chuidiang.examples;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class RestIT {
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
}