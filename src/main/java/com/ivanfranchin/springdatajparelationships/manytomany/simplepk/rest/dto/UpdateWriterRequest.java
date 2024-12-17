package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateWriterRequest(@Schema(example = "Steve Jobs") String name) {
}
