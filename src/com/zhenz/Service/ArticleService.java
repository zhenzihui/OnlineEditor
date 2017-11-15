package com.zhenz.Service;

import com.zhenz.Entity.Article;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
public interface ArticleService {
    Article get(int id);
    List<Article> get();
    List<Article> getArticleByUserId(int userId);
    int add(Article article);
    void delete(int id);
    void update(Article article);

}
