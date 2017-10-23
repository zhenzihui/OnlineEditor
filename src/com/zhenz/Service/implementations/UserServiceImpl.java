package com.zhenz.Service.implementations;

import com.zhenz.DAO.UserDao;
import com.zhenz.Entity.Article;
import com.zhenz.Entity.User;
import com.zhenz.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */
public class UserServiceImpl implements UserService {
    private User user;

    @Autowired
    HttpSession session;

    @Autowired
    UserDao userDao;





    @Override
    public User login(User user) {

        return userDao.login(user);
    }

    @Override
    public void logout() {
        session.setAttribute("user",null);
    }


    @Override
    public List<Article> articles() {
        return userDao.articles(user);
    }

    @Override
    public int register(User user) {
      return   userDao.register(user);
    }
}
