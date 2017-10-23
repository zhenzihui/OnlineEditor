package com.zhenz.Controller;

import com.zhenz.Entity.Article;
import com.zhenz.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/10/23.
 */
@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/{id}")
    public ModelAndView get(@RequestParam int id)
    {

        Article article= articleService.get(id);
        

    }




}
