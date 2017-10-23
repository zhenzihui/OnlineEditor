package com.zhenz.Service;

import com.zhenz.Entity.Article;
import com.zhenz.Entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface UserService {
    User login(User user);
    void logout();
    List<Article> articles();
    int register(User user);


}
