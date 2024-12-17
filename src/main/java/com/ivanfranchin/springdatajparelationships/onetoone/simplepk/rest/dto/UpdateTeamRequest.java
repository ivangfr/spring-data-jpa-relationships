package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateTeamRequest(@Schema(example = "Black Team") String name) {
}
