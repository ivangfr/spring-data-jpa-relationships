package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.service;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Comment;

public interface CommentService {

    Comment validateAndGetComment(Long id);

    Comment saveComment(Comment comment);

    void deleteComment(Comment comment);
}
