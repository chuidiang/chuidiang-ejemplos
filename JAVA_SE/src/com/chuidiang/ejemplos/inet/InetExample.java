package com.chuidiang.ejemplos.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by chuidiang on 25/05/17.
 */
public class InetExample {
    public static void main(String[] args) {
        try {
            InetAddress giriAddress = InetAddress.getByName("www.google.com");
            System.out.println(giriAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
