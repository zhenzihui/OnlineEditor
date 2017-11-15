package com.zhenz.DAO;

import com.zhenz.Entity.Article;
import com.zhenz.Entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface UserDao {
    User login(User user);
    List<Article> articles(User user);
    int register(User user);
    List<User> all();
}
