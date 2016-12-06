package com.chuidiang.ejemplos.chat;

import java.io.Serializable;
import java.util.Date;

public class ChatData implements Serializable {
    private static final long serialVersionUID = 2015942905269624152L;
    public Date date;
    public String user;
    public String text;

    @Override
    public String toString() {
        return "" + date + " " + user + ": " + text;
    }
}
