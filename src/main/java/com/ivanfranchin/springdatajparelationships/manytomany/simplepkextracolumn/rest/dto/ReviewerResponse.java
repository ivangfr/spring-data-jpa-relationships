package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto;

import java.util.Set;

public record ReviewerResponse(Long id, String name, Set<Comment> comments) {

    public record Comment(Long id, String text, Article article) {

        public record Article(Long id) {
        }
    }
}
