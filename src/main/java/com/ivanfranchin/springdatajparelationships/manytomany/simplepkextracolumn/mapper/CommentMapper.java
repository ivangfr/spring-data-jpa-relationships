package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.mapper;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Comment;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.CommentResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.CreateCommentRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.service.ArticleService;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.service.ReviewerService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {ReviewerService.class, ArticleService.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reviewer", source = "reviewerId")
    @Mapping(target = "article", source = "articleId")
    Comment toComment(CreateCommentRequest createCommentRequest);

    CommentResponse toCommentResponse(Comment comment);
}
