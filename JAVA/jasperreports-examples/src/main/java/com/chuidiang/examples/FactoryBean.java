package com.chuidiang.examples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author fjabellan 31/10/2023
 */
public class FactoryBean {
    private static String [] names = new String [] {"Pedro", "Antonio", "Isabel", "Lidia"};

    private static Date [] birthdays = new Date [] {new Date(2002,11,3),
            new Date(1997,4,22),
            new Date(1665,4,11),
            new Date(2001,2,23)};

    private static String [] mails = new String [] {"Pedro@gmail.com", "Antonio@gmail.com",
            "Isabel@gmail.com", "Lidia@gmail.com"};

    public static Collection<Bean> create(){
        List<Bean> beans = new ArrayList<>();
        for (int i=0;i<names.length;i++) {
            beans.add(new Bean(names[i], birthdays[i], mails[i]));
        }
        return beans;
    }
}
