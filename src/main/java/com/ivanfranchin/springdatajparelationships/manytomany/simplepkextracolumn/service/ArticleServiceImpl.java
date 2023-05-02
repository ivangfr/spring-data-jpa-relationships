package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.service;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.exception.ArticleNotFoundException;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Article;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article validateAndGetArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }
}
