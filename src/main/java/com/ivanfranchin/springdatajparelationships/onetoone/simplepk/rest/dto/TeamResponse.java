package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto;

public record TeamResponse(Long id, String name, TeamDetailResponse teamDetail) {
}
