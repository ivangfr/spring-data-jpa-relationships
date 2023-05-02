package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.repository;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
