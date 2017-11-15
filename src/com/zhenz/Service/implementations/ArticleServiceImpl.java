package com.zhenz.Service.implementations;

import com.zhenz.DAO.ArticleDao;
import com.zhenz.Entity.Article;
import com.zhenz.Service.ArticleService;
import com.zhenz.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private LogService logService;


    @Override
    public Article get(int id) {
        return articleDao.get(id);
    }

    @Override
    public List<Article> get() {
        return articleDao.all();
    }

    @Override
    public List<Article> getArticleByUserId(int userId) {

        return articleDao.getArticleByUserId(userId);

    }

    @Override
    public int add(Article article) {


       return articleDao.add(article);
    }

    @Override
    public void delete(int id) {
        articleDao.delete(id);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }


}
