package com.zhenz.utils;

import com.google.gson.Gson;

public class Message {
    public static String success="ok";
    public static String failure="err";
    private String status;
    private static Gson gson;
    public Message(String msg)
    {
        this.status=msg;
    }
    public Message(){
        gson=new Gson();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
