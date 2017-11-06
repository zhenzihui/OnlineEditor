package com.zhenz.utils;

public class Message {
    public static String success="ok";
    public static String failure="err";
    private String status;

    public Message(String msg)
    {
        this.status=msg;
    }
    public Message(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
