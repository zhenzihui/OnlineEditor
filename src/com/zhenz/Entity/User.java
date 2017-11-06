package com.zhenz.Entity;

/**
 * Created by Administrator on 2017/10/17.
 */
public class User {
    int id;
    String email;
    String password;
    char type;
    int article_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getArticle_count() {
        return article_count;
    }

    public void setArticle_count(int article_count) {
        this.article_count = article_count;
    }

    public boolean owns(Article article)
    {
        return this.getId()==article.getUser_id();
    }

}
