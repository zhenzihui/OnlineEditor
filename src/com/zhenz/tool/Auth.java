package com.zhenz.tool;

import com.zhenz.Entity.User;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/10/17.
 */
public class Auth {
    private static User user;

    public static User User(HttpSession session,String attr)
    {
        return (User)session.getAttribute(attr);
    }

    public static boolean IsAuthenticated()
    {
        return user!=null;
    }

    public static void Logout()
    {
        user=null;
    }

    public static User Login(User user)
    {

    }

}
