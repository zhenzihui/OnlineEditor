package com.zhenz.Controller;


import com.zhenz.DAO.ArticleDao;
import com.zhenz.Entity.Article;
import com.zhenz.Service.ArticleService;
import com.zhenz.Service.implementations.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/10/11.
 */

public class Hello implements Controller {
    @Autowired
    ArticleService articleService;
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {



        Article article = articleService.get(1);

        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("article",article);
        return mv;
    }
}
