package com.zhenz.Controller;


import com.google.gson.Gson;
import com.zhenz.Entity.Log;
import com.zhenz.Entity.User;
import com.zhenz.Service.ArticleService;

import com.zhenz.Service.LogService;
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


    @Autowired
    LogService logService;

    Log log;
    Message message;
    Gson gson=new Gson();
    @RequestMapping(value = "/{id}.do",method = RequestMethod.GET)
    public ModelAndView get(@PathVariable("id") int id)
    {
        Article article= articleService.get(id);
        log=new Log();
        log.setType(Log.Action_View);
        logService.newLog(log);

        ModelAndView mv=new ModelAndView("article/ArticleView");
        mv.addObject(article);
        return mv;

    }

    @RequestMapping(value = "/all.do",method = RequestMethod.GET)
    public ModelAndView get(HttpSession session)
    {
        User user=(User)session.getAttribute("user");
        List<Article> articles=articleService.get();
        ModelAndView mv =new ModelAndView();
        if(!user.getType().equals("A"))
        {
            mv.setViewName("error_pages/ACL_error");
        }else {
            mv.setViewName("article/ArticleList");
            mv.addObject("articles",articles);
        }


        return mv;
    }

    @RequestMapping(value = "/my.do",method = RequestMethod.GET)
    public ModelAndView getByUser(HttpSession session)
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
        log=new Log();
        Article article =new Article();
      try {
          User user = (User) session.getAttribute("user");
          article.setUser_id(user.getId());
          article.setBody(body);
          article.setTitle(title);
          int wordcount= wordCount(article.getBody());
          article.setWordCount(wordcount);
          int id= articleService.add(article);
          log.setArticle_id(id);
          log.setUser_id(user.getId());
          log.setType(Log.Action_Insert);
          logService.newLog(log);

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
        ModelAndView mv;
        User user=(User)session.getAttribute("user");
        Article article = articleService.get(id);
       mv= new ModelAndView();
        if(user.getId()==article.getUser_id())
        {
            mv.setViewName("article/ArticleUpdate");
            mv.addObject("article", article);
            session.setAttribute("article", article);
        }else
            {
             mv.setViewName("error_pages/ACL_error");
            }

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
            log=new Log();
            log.setType(Log.Action_Update);
            log.setArticle_id(article.getId());
            log.setUser_id(user.getId());
            logService.newLog(log);
           message.setStatus(Message.success);
        }else
            {
               message.setStatus(Message.failure);
            }

        return message.toString();

    }

    @ResponseBody
    @RequestMapping(value = "/{id}/delete.do",method = RequestMethod.POST)
    public String delete(@PathVariable("id") int id,HttpSession session)
    {
        message=new Message();
        User user=(User)session.getAttribute("user");
        articleService.delete(id);
        log=new Log();
        log.setUser_id(user.getId());
        log.setArticle_id(id);
        log.setType(Log.Action_Delete);
        logService.newLog(log);
        message.setStatus(Message.success);
        return message.toString();
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

}
