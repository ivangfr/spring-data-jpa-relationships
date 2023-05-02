package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto;

public record CommentResponse(Long id, Reviewer reviewer, Article article, String text) {

    public record Reviewer(Long id, String name) {
    }

    public record Article(Long id, String title) {
    }
}
