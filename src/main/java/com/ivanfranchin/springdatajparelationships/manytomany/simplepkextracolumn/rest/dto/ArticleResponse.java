package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto;

import java.util.Set;

public record ArticleResponse(Long id, String title, Set<Comment> comments) {

    public record Comment(Long id, String text, Reviewer reviewer) {

        public record Reviewer(Long id) {
        }
    }
}
