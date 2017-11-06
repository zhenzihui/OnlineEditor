package com.zhenz.Controller;


import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.xml.internal.ws.api.model.MEP;
import com.zhenz.Entity.User;
import com.zhenz.Service.ArticleService;

import com.zhenz.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.zhenz.Entity.Article;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/23.
 */
@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    Message message;
    Gson gson=new Gson();
    @RequestMapping(value = "/{id}.do",method = RequestMethod.GET)
    public ModelAndView get(@PathVariable("id") int id)
    {

        Article article= articleService.get(id);
        ModelAndView mv=new ModelAndView("article/ArticleView");
        mv.addObject(article);
        return mv;

    }

    @RequestMapping(value = "/all.do",method = RequestMethod.GET)
    public ModelAndView get()
    {
        List<Article> articles=articleService.get();
        ModelAndView mv =new ModelAndView("article/ArticleList");
        mv.addObject("articles",articles);
        return mv;
    }

    @RequestMapping(value = "/my.do",method = RequestMethod.GET)
    public ModelAndView get(HttpSession session)
    {
        ModelAndView mv = new ModelAndView("article/ArticleList");
        User user=(User)session.getAttribute("user");
        if(user!=null) {
            List<Article> articles = articleService.getArticleByUserId(user.getId());

            mv.addObject("articles", articles);
            return mv;
        }else
            {
                mv.setStatus(HttpStatus.FORBIDDEN);
                return mv;
            }
    }



    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestParam String title, @RequestParam String body, HttpSession session, HttpServletResponse response)
    {
        Article article =new Article();
      try {
          User user = (User) session.getAttribute("user");
          article.setUser_id(user.getId());

          article.setBody(body);
          article.setTitle(title);

          int wordcount= wordCount(article.getBody());
          article.setWordCount(wordcount);
          articleService.add(article);
          return "{result:'ok'}";
      }catch (Exception e)
      {
          e.printStackTrace();
      }
            response.setStatus(500);
         return null;


    }


    @RequestMapping(value="/{id}/update.do",method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") int id,HttpSession session)
    {
        ModelAndView mv = new ModelAndView("article/ArticleUpdate");
        Article article=articleService.get(id);
        mv.addObject("article",article);
        session.setAttribute("article",article);
        return mv;
    }


    /**
     * 表单中已填充id、title、body，需要自行填充wordcount
     * */
    @RequestMapping(value = "/{id}.do",method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestParam String title,@RequestParam String body,@PathVariable("id") int id,HttpSession session)
    {

        User user=(User)session.getAttribute("user");
        Article article=(Article) session.getAttribute("article");
        article.setTitle(title);
        article.setBody(body);
        article.setId(id);
        article.setWordCount(wordCount(article.getBody()));
        message=new Message();
        if(user.owns(article))
        {
            articleService.update(article);

           message.setStatus(Message.success);
        }else
            {
               message.setStatus(Message.failure);
            }

        return gson.toJson(message);

    }

    @ResponseBody
    @RequestMapping(value = "/{id}/delete.do",method = RequestMethod.POST)
    public String delete(@PathVariable("id") int id)
    {
        articleService.delete(id);
        return "{'status':'ok'}";
    }





    private int wordCount(String md)
    {
        int n=0;
        //匹配汉字、字母、数字
        String reg="/^[a-zA-Z0-9\\u4e00-\\u9fa5\\,，]+$/";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher=pattern.matcher(md);
        while (matcher.find())
        {
            n++;
        }
        return n;
    }
    private boolean owns(int user_id,int id)
    {
        return user_id==id;
    }
}
