package com.zhenz.DAO;

import com.zhenz.Entity.Article;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
public interface ArticleDao {
    Article get(int id);
    List<Article> all();
    List<Article> getArticleByUserId(int userId);
    void add(Article article);
    void delete(int id);
    void update(Article article);

}
