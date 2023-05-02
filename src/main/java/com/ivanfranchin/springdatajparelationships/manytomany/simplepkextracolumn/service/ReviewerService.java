package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.service;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model.Reviewer;

public interface ReviewerService {

    Reviewer validateAndGetReviewer(Long id);

    Reviewer saveReviewer(Reviewer reviewer);

    void deleteReviewer(Reviewer reviewer);
}
