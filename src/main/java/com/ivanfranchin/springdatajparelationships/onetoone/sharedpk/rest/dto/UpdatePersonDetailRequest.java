package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdatePersonDetailRequest(@Schema(example = "New information about the person") String description) {
}
