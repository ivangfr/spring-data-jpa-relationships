package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.repository;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
