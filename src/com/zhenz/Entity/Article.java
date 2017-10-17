package com.zhenz.Entity;


import java.io.Serializable;

import java.security.spec.ECField;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/11.
 */
public class Article implements Serializable {
    private int id;
    private String title;
    private String body;
    private Timestamp created_at;
    private Timestamp updated_at;
    private int user_id;
    private int wordCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    private Date toDate(Timestamp timestamp)
    {
        Date mDate=null;
        String tsStr="";
        DateFormat df=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        tsStr=df.format(timestamp);
        try{
        mDate=df.parse(tsStr);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return mDate;
    }

    private Timestamp toTimestamp(Date date)
    {
        String dateString;
        Timestamp timestamp;
        DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try{
            dateString=dateFormat.format(date);
            timestamp=Timestamp.valueOf(dateString);
            return timestamp;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    return null;

    }

}


