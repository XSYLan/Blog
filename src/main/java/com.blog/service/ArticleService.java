package com.blog.service;

import com.blog.model.Article;

import java.util.List;

/**
 * Created by xuesiyuan on 2018/3/30.
 */
public interface ArticleService {
    Article selectById (Integer id);

    Article selectLastArticle (Integer id);

    Article selectNextArticle (Integer id);

    List<Article> queryAll();

    int countAllNum();

    boolean updateArticle(Article article);

    int deleteById(Integer id);

    int selectCount();

    List<Article> selectByWord(String word);

    boolean insert(Article article);

}
