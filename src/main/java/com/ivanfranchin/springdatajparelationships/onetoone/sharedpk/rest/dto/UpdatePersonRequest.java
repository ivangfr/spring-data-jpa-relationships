package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdatePersonRequest(@Schema(example = "Ivan Franchin 2") String name) {
}
