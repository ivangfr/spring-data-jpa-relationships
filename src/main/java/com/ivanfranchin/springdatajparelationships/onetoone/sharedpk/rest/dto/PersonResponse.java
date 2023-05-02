package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto;

public record PersonResponse(Long id, String name, PersonDetailResponse personDetail) {
}
