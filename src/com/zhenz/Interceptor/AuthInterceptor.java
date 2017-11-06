package com.zhenz.Interceptor;

import com.zhenz.Entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/10/17.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user=(User)session.getAttribute("user");
        session.setAttribute("intended",request.getRequestURI());
        return user!=null;

    }
}
