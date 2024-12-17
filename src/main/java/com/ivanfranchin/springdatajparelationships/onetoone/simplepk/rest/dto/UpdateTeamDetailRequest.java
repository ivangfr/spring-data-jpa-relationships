package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateTeamDetailRequest(@Schema(example = "This team is excellent") String description) {
}
