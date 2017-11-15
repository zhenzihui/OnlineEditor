package com.zhenz.Interceptor;

import com.zhenz.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ACLInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    HttpSession session;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user=(User)session.getAttribute("user");
        if(user!=null)
        {
            return user.getType().equals("A");
        }
        return false;
    }

}
