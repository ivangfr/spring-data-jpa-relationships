package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.service;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Article;

public interface ArticleService {

    Article validateAndGetArticle(Long id);

    Article createArticle(Article article);

    void deleteArticle(Article article);
}
