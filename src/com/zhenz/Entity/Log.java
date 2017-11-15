package com.zhenz.Entity;

import java.sql.Date;

public class Log {
    public static String Action_Delete="Action_Delete";
    public static String Action_Insert="Action_Insert";
    public static String Action_Update="Action_Update";
    public static String Action_View="Action_View";

    private int id;
    private int user_id;
    private String type;
    private int article_id;
    private Date timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
