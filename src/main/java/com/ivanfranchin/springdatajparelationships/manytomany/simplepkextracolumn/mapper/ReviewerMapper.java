package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.mapper;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Reviewer;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.CreateReviewerRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto.ReviewerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Reviewer toReviewer(CreateReviewerRequest createReviewerRequest);

    ReviewerResponse toReviewerResponse(Reviewer reviewer);
}
